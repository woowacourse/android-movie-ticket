package woowacourse.movie.presentation.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.utils.ImageConverter

class MovieScreenAdapter(
    private val context: Context,
    private val movies: List<Movie>,
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
        setViewHolder(movie)
        setClickListener(movie)
        return view
    }

    private fun makeViewHolder(
        view: View,
        movie: Movie,
    ) {
        movieViewHolder =
            MovieViewHolder(
                view.findViewById(R.id.movie_title),
                view.findViewById(R.id.movie_poster),
                view.findViewById(R.id.movie_screening_date),
                view.findViewById(R.id.movie_running_time),
                view.findViewById(R.id.movie_reservation_button),
                onMovieSelected = { onMovieSelected(movie.movieId) },
            )
        view.tag = movieViewHolder
    }

    private fun setViewHolder(movie: Movie) {
        movieViewHolder.title.text = movie.title
        val imageResource = ImageConverter.getDrawableIdByName(context, movie.imageName)
        imageResource?.let { movieViewHolder.poster.setImageResource(it) }
        movieViewHolder.screeningDate.text = movie.screeningDate
        movieViewHolder.runningTime.text = movie.runningTime.toString()
    }

    private fun setClickListener(movie: Movie) {
        movieViewHolder.movieReservationButton.setOnClickListener {
            onMovieSelected(movie.movieId)
        }
    }
}
