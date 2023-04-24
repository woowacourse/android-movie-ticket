package woowacourse.movie.reservation

import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieRecyclerItem
import java.time.format.DateTimeFormatter

class MovieInfoView(
    private val movieInformationView: ScrollView
) {

    fun bind(movie: MovieRecyclerItem.MovieInfo) {
        val posterImageView =
            movieInformationView.findViewById<ImageView>(R.id.reservation_movie_image_view)
        val movieNameTextView =
            movieInformationView.findViewById<TextView>(R.id.reservation_movie_name_text_view)
        val screeningDateTextView =
            movieInformationView.findViewById<TextView>(R.id.reservation_movie_screening_date_text_view)
        val runningTimeTextView =
            movieInformationView.findViewById<TextView>(R.id.reservation_movie_running_time_text_view)
        val descriptionTextView =
            movieInformationView.findViewById<TextView>(R.id.reservation_movie_description_text_view)
        val screeningPeriodForm =
            movieInformationView.context.getString(R.string.screening_period_form)
        val runningTimeFormat =
            movieInformationView.context.getString(R.string.running_time_form)

        with(movie) {
            posterImageView.setImageResource(posterImage)
            movieNameTextView.text = movieName
            screeningDateTextView.text = screeningPeriodForm.format(
                startDate.format(DATE_TIME_FORMAT),
                endDate.format(DATE_TIME_FORMAT)
            )
            runningTimeTextView.text = runningTimeFormat.format(runningTime)
            descriptionTextView.text = description
        }
    }

    companion object {
        private val DATE_TIME_FORMAT = DateTimeFormatter.ISO_DATE
    }
}
