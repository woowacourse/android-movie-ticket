package woowacourse.movie.presentation.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie

class MovieScreenAdapter(
    private val context: Context,
    private var movies: List<Movie> = listOf(),
    private val onMovieSelected: (Int) -> Unit,
) : BaseAdapter() {
    private lateinit var movieViewHolder: MovieViewHolder

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val movie = movies[position]
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_item, null)
            makeViewHolder(
                view = view,
                movie = movie,
            )
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }
        return view
    }

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        this.notifyDataSetChanged()
    }

    private fun makeViewHolder(
        view: View,
        movie: Movie,
    ) {
        movieViewHolder =
            MovieViewHolder(
                view = view,
                movie = movie,
                context = context,
                onMovieSelected = onMovieSelected,
            )
    }
}
