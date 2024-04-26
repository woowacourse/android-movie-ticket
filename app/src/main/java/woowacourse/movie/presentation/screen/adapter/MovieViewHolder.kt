package woowacourse.movie.presentation.screen.adapter

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.utils.toCustomString
import woowacourse.movie.presentation.utils.toDrawableIdByName

class MovieViewHolder(
    view: View,
    private val context: Context,
    private val onMovieSelected: (Int) -> Unit,
) : RecyclerView.ViewHolder(view){
    private val title: TextView = view.findViewById(R.id.movie_title)
    private val poster: ImageView = view.findViewById(R.id.movie_poster)
    private val screeningDate: TextView = view.findViewById(R.id.movie_screening_date)
    private val runningTime: TextView = view.findViewById(R.id.movie_running_time)
    private val movieReservationButton: Button = view.findViewById(R.id.movie_reservation_button)

    fun bind(movie: Movie) {
        title.text = movie.title
        val imageResource = movie.imageName.toDrawableIdByName(context)
        imageResource?.let { poster.setImageResource(it) }
        screeningDate.text = "${movie.screeningStartDate.toCustomString()} ~ ${movie.screeningEndDate.toCustomString()}"
        runningTime.text = movie.runningTime.toString()

        setClickListener(movie)
    }

    private fun setClickListener(movie: Movie) {
        movieReservationButton.setOnClickListener {
            onMovieSelected(movie.movieId)
        }
    }
}
