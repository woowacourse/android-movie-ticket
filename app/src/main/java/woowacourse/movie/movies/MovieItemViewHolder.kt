package woowacourse.movie.movies

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieInfo
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class MovieItemViewHolder(
    val itemView: View
) {
    private val movieNameTextView: TextView =
        itemView.findViewById(R.id.movie_name_text_view)
    private val movieImageView: ImageView =
        itemView.findViewById(R.id.movie_image_view)
    private val screeningDateTextView: TextView =
        itemView.findViewById(R.id.movie_screening_date_text_view)
    private val movieRunningTimeTextView: TextView =
        itemView.findViewById(R.id.movie_running_time_text_view)
    private val reservationButton: Button =
        itemView.findViewById(R.id.reservation_button)

    fun setView(movie: MovieInfo, onReservationButtonClicked: (movie: MovieInfo) -> Unit) {
        movieNameTextView.text = movie.movieName
        movie.posterImage?.let {
            movieImageView.setImageResource(it)
        }
        screeningDateTextView.text = itemView.context
            .getString(R.string.screening_period_form)
            .format(
                movie.startDate.format(
                    DateTimeFormatter.ofLocalizedDate(
                        FormatStyle.MEDIUM
                    )
                ),
                movie.endDate.format(
                    DateTimeFormatter.ofLocalizedDate(
                        FormatStyle.MEDIUM
                    )
                )
            )
        movieRunningTimeTextView.text = itemView
            .context
            .getString(R.string.running_time_form)
            .format(movie.runningTime)
        reservationButton.setOnClickListener {
            onReservationButtonClicked(movie)
        }
    }
}
