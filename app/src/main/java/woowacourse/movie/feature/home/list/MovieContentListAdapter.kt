package woowacourse.movie.feature.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.ui.DateUi

class MovieContentListAdapter(
    private val movieContents: List<MovieContent>,
    private val reservationButtonClickListener: ReservationButtonClickListener,
) : BaseAdapter() {
    private class MovieContentViewHolder(private val view: View) {
        private val posterImage: ImageView by lazy { view.findViewById(R.id.poster_image) }
        private val titleText: TextView by lazy { view.findViewById(R.id.title_text) }
        private val screeningDateText: TextView by lazy { view.findViewById(R.id.screening_date_text) }
        private val runningTimeText: TextView by lazy { view.findViewById(R.id.running_time_text) }
        private val reservationButton: Button by lazy { view.findViewById(R.id.reservation_button) }

        fun setUpMovieContentUi(movieContent: MovieContent) {
            movieContent.run {
                posterImage.setImageResource(imageId)
                titleText.text = title
                screeningDateText.text = DateUi.screeningDateMessage(screeningDate, view.context)
                runningTimeText.text =
                    view.context.resources.getString(R.string.running_time).format(runningTime)
            }
        }

        fun setOnClickReservationButton(
            movieContentId: Long,
            reservationButtonClickListener: ReservationButtonClickListener,
        ) {
            reservationButton.setOnClickListener {
                reservationButtonClickListener.onClick(it, movieContentId)
            }
        }
    }

    override fun getCount(): Int = movieContents.size

    override fun getItem(position: Int): MovieContent = movieContents[position]

    override fun getItemId(position: Int): Long = getItem(position).id

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val viewHolder: MovieContentViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_movie_content, parent, false)
            viewHolder = MovieContentViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as MovieContentViewHolder
        }

        viewHolder.setUpMovieContentUi(getItem(position))
        viewHolder.setOnClickReservationButton(getItemId(position), reservationButtonClickListener)

        return view
    }
}
