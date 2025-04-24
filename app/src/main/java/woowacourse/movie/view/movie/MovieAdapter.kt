package woowacourse.movie.view.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.view.ReservationUiFormatter

class MovieAdapter(
    private val movies: List<Movie>,
    private val clickListener: MovieClickListener,
) : BaseAdapter() {
    private val reservationUiFormatter: ReservationUiFormatter by lazy { ReservationUiFormatter() }

    override fun getCount(): Int {
        val adCount = movies.size / 3
        return movies.size + adCount
    }

    override fun getItem(position: Int): Any = movies[getMovieIndex(position)]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int =
        if (position % 4 == 3) {
            VIEW_TYPE_AD
        } else {
            VIEW_TYPE_MOVIE
        }

    private class MovieViewHolder(
        val view: View,
        val clickListener: MovieClickListener,
        private val reservationUiFormatter: ReservationUiFormatter,
    ) {
        val titleTextView: TextView = view.findViewById(R.id.tv_movie_title)
        val posterImageView: ImageView = view.findViewById(R.id.iv_movie_poster)
        val screeningDateTextView: TextView = view.findViewById(R.id.tv_movie_screening_date)
        val runningTimeTextView: TextView = view.findViewById(R.id.tv_movie_running_time)
        val reservationButton: Button = view.findViewById(R.id.btn_movie_reservation)

        fun bind(movie: Movie) {
            titleTextView.text = movie.title
            posterImageView.setImageResource(movie.poster)
            screeningDateTextView.text =
                view.context.getString(
                    R.string.movie_screening_date,
                    reservationUiFormatter.localDateToUI(movie.startDate),
                    reservationUiFormatter.localDateToUI(movie.endDate),
                )
            runningTimeTextView.text =
                view.context.getString(R.string.movie_running_time, movie.runningTime)
            reservationButton.setOnClickListener { clickListener.onReservationClick(movie) }
        }
    }

    private class AdViewHolder(
        view: View,
    ) {
        val adImageVIew: ImageView = view.findViewById(R.id.iv_advertisement)

        fun bind() {
            adImageVIew.setImageResource(R.drawable.advertisement)
        }
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View

        val viewType = getItemViewType(position)

        return if (viewType == VIEW_TYPE_MOVIE) {
            val viewHolder: MovieViewHolder
            if (convertView == null) {
                view =
                    LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
                viewHolder =
                    MovieViewHolder(view, clickListener, reservationUiFormatter)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as MovieViewHolder
            }

            val movie = movies[getMovieIndex(position)]
            viewHolder.bind(movie)
            view
        } else {
            val viewHolder: AdViewHolder
            if (convertView == null) {
                view =
                    LayoutInflater
                        .from(parent?.context)
                        .inflate(R.layout.item_advertisement, parent, false)
                viewHolder = AdViewHolder(view)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as AdViewHolder
            }

            viewHolder.bind()
            view
        }
    }

    private fun getMovieIndex(position: Int): Int = position - (position / 4)

    companion object {
        private const val VIEW_TYPE_MOVIE = 0
        private const val VIEW_TYPE_AD = 1
    }
}
