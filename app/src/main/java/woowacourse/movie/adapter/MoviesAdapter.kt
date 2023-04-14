package woowacourse.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import domain.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.viewholder.MovieItemViewHolder

class MoviesAdapter(
    private val movies: List<Movie>
) : BaseAdapter() {

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val context: Context? = parent?.context
        val movie: Movie = movies[position]

        val movieItemViewHolder: MovieItemViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, null)

            movieItemViewHolder = MovieItemViewHolder(
                moviePosterImageView = view.findViewById(R.id.movie_image_view),
                movieNameTextView = view.findViewById(R.id.movie_name_text_view),
                screeningDateTextView = view.findViewById(R.id.movie_screening_date_text_view),
                runningTimeTextView = view.findViewById(R.id.movie_running_time_text_view),
                reservationButton = view.findViewById(R.id.reservation_button)
            )

            view.tag = movieItemViewHolder

            movieItemViewHolder.setPosterResource(movie)
            movieItemViewHolder.setMovieNameText(movie)
            movieItemViewHolder.setReservationButton(context, movie)
            movieItemViewHolder.setScreeningDateText(context, movie)
            movieItemViewHolder.setRunningTimeText(context, movie)
        } else {
            view = convertView
            movieItemViewHolder = view.tag as MovieItemViewHolder

            movieItemViewHolder.setPosterResource(movie)
            movieItemViewHolder.setMovieNameText(movie)
            movieItemViewHolder.setScreeningDateText(context, movie)
            movieItemViewHolder.setRunningTimeText(context, movie)
        }

        return view
    }
}
