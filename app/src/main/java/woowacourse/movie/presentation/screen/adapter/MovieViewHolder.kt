package woowacourse.movie.presentation.screen.adapter

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.utils.toCustomString
import woowacourse.movie.utils.toDrawableIdByName

class MovieViewHolder(
    private val view: View,
    private val movie: Movie,
    private val context: Context,
    private val onMovieSelected: (Int) -> Unit,
) {
    private val title: TextView = view.findViewById(R.id.movie_title)
    private val poster: ImageView = view.findViewById(R.id.movie_poster)
    private val screeningDate: TextView = view.findViewById(R.id.movie_screening_date)
    private val runningTime: TextView = view.findViewById(R.id.movie_running_time)
    private val movieReservationButton: Button = view.findViewById(R.id.movie_reservation_button)

    init {
        setViewHolderValues()
        setClickListener()
        view.tag = this@MovieViewHolder
    }

    private fun setViewHolderValues() {
        title.text = movie.title
        val imageResource = movie.imageName.toDrawableIdByName(context)
        imageResource?.let { poster.setImageResource(it) }
        screeningDate.text = movie.screeningDate.toCustomString()
        runningTime.text = movie.runningTime.toString()
    }

    private fun setClickListener() {
        movieReservationButton.setOnClickListener {
            onMovieSelected(movie.movieId)
        }
    }
}
