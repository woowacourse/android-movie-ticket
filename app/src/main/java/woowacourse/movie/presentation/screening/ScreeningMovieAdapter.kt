package woowacourse.movie.presentation.screening

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class ScreeningMovieAdapter(
    private val onClickReservationButton: (id: Long) -> Unit = {},
) : BaseAdapter() {
    private var movies: List<ScreeningMovieUiModel> = emptyList()
    private lateinit var inflater: LayoutInflater

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): ScreeningMovieUiModel = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent?.context)
        val viewHolder: ViewHolder

        return if (convertView == null) {
            inflater.inflate(R.layout.item_screening_movie, parent, false).also { view ->
                viewHolder = ViewHolder(view, onClickReservationButton)
                view.tag = viewHolder
            }
        } else {
            viewHolder = convertView.tag as ViewHolder
            convertView
        }.apply {
            viewHolder.bind(getItem(position))
        }
    }

    fun updateMovies(newMovies: List<ScreeningMovieUiModel>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    class ViewHolder(
        view: View,
        private val onClickReservationButton: (id: Long) -> Unit = {},
    ) {
        private val postView: ImageView = view.findViewById(R.id.iv_movie_post)
        private val titleView: TextView = view.findViewById(R.id.tv_movie_title)
        private val dateView: TextView = view.findViewById(R.id.tv_movie_running_date)
        private val runningTimeView: TextView = view.findViewById(R.id.tv_movie_running_time)
        private val reservationButton: Button = view.findViewById(R.id.btn_movie_reservation)
        private var reservationButtonClickListener: (() -> Unit)? = null

        init {
            reservationButton.setOnClickListener {
                reservationButtonClickListener?.invoke()
                    ?: error("Reservation button click listener 는 초기화 되지 않았습니다.")
            }
        }

        fun bind(movie: ScreeningMovieUiModel) {
            val (id, title, imageRes, screenDate, runningTime) = movie
            postView.setImageResource(imageRes)
            titleView.text = title
            dateView.text = screenDate
            runningTimeView.text = runningTime
            reservationButtonClickListener = { onClickReservationButton(id) }
        }
    }
}
