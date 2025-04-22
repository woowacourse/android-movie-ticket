package woowacourse.movie.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.model.MovieUiModel

class MoviesAdapter(
    private val movies: List<MovieUiModel>,
    private val onBookingClick: (MovieUiModel) -> Unit,
) : BaseAdapter() {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val movieViewHolder: MovieViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }

        movieViewHolder.bind(movies[position]) { movie -> onBookingClick(movie) }

        return view
    }

    override fun getItem(position: Int): MovieUiModel = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = movies.size

    private class MovieViewHolder(
        private val view: View,
    ) {
        private val title: TextView = view.findViewById(R.id.tv_movie_title)
        private val poster: ImageView = view.findViewById(R.id.iv_movie_poster)
        private val date: TextView = view.findViewById(R.id.tv_movie_date)
        private val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
        private val bookingButton: Button = view.findViewById(R.id.btn_movie_booking)

        fun bind(
            movie: MovieUiModel,
            onBookingClick: (MovieUiModel) -> Unit,
        ) {
            title.text = movie.title
            poster.setImageResource(movie.poster)

            date.text =
                view.context.getString(
                    R.string.movies_movie_date_with_tilde,
                    movie.startDate,
                    movie.endDate,
                )

            runningTime.text =
                view.context.getString(
                    R.string.movies_movie_running_time,
                    movie.runningTime,
                )

            bookingButton.setOnClickListener { onBookingClick(movie) }
        }
    }
}
