package woowacourse.movie.presentation.screen.adapter

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MovieViewHolder(
    val title: TextView,
    val poster: ImageView,
    val screeningDate: TextView,
    val runningTime: TextView,
    val movieReservationButton: Button,
    val onMovieSelected: () -> Unit,
) {
    init {
        movieReservationButton.setOnClickListener {
            onMovieSelected()
        }
    }
}
