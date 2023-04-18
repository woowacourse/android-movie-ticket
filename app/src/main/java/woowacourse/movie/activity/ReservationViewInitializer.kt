package woowacourse.movie.activity

import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.model.ActivityMovieModel
import java.time.format.DateTimeFormatter

class ReservationViewInitializer(
    private val descriptionTextView: TextView,
    private val runningTimeTextView: TextView,
    private val screeningDateTextView: TextView,
    private val movieNameTextView: TextView,
    private val posterImageView: ImageView,
) {

    fun init(movie: ActivityMovieModel, runningTimeFormat: String, screeningPeriodFormat: String) {
        with(movie) {
            val dateFormat: DateTimeFormatter = DateTimeFormatter.ISO_DATE

            posterImage?.let { id -> posterImageView.setImageResource(id) }
            movieNameTextView.text = movieName
            screeningDateTextView.text = screeningPeriodFormat.format(
                startDate.format(dateFormat),
                endDate.format(dateFormat)
            )
            runningTimeTextView.text = runningTimeFormat.format(runningTime)
            descriptionTextView.text = description
        }
    }
}
