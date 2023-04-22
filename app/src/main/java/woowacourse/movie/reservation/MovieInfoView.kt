package woowacourse.movie.reservation

import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.DisplayItem
import java.time.format.DateTimeFormatter

class MovieInfoView(
    private val movieInformationView: ScrollView
) {

    fun bind(movie: DisplayItem.MovieInfo) {
        val posterImageView =
            findViewConfiguration<ImageView>(R.id.reservation_movie_image_view)
        val movieNameTextView =
            findViewConfiguration<TextView>(R.id.reservation_movie_name_text_view)
        val screeningDateTextView =
            findViewConfiguration<TextView>(R.id.reservation_movie_screening_date_text_view)
        val runningTimeTextView =
            findViewConfiguration<TextView>(R.id.reservation_movie_running_time_text_view)
        val descriptionTextView =
            findViewConfiguration<TextView>(R.id.reservation_movie_description_text_view)
        val screeningPeriodForm =
            movieInformationView.context.getString(R.string.screening_period_form)
        val runningTimeFormat =
            movieInformationView.context.getString(R.string.running_time_form)

        with(movie) {
            posterImage?.let { id -> posterImageView.setImageResource(id) }
            movieNameTextView.text = movieName
            screeningDateTextView.text = screeningPeriodForm.format(
                startDate.format(DATE_TIME_FORMAT),
                endDate.format(DATE_TIME_FORMAT)
            )
            runningTimeTextView.text = runningTimeFormat.format(runningTime)
            descriptionTextView.text = description
        }
    }

    private fun <T : View?> findViewConfiguration(resourceId: Int): T {
        return movieInformationView.findViewById<T>(resourceId)
    }

    companion object {
        private val DATE_TIME_FORMAT = DateTimeFormatter.ISO_DATE
    }
}
