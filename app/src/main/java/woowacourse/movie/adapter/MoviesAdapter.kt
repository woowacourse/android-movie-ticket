package woowacourse.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.movie.Movie
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
            )
            view.tag = movieItemViewHolder
        } else {
            view = convertView
            movieItemViewHolder = view.tag as MovieItemViewHolder
        }

        movieItemViewHolder.setViewContents(context, movie)
        return view
    }
}
