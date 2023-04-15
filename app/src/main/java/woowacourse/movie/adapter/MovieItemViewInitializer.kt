package woowacourse.movie.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import domain.movie.Movie
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class MovieItemViewInitializer {

    fun init(movie: Movie, view: View) {
        val movieNameTextView: TextView = view.findViewById(R.id.movie_name_text_view)
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val movieImageView: ImageView = view.findViewById(R.id.movie_image_view)
        val screeningDateTextView: TextView = view.findViewById(R.id.movie_screening_date_text_view)
        val runningTimeTextView: TextView = view.findViewById(R.id.movie_running_time_text_view)

        movieNameTextView.text = movie.name.value
        movie.posterImage?.let { movieImageView.setImageResource(it) }
        screeningDateTextView.text = view.context
            .getString(R.string.screening_period_form)
            .format(
                movie.screeningPeriod.startDate.value.format(dateFormat),
                movie.screeningPeriod.endDate.value.format(dateFormat)
            )
        runningTimeTextView.text = view.context
            .getString(R.string.running_time_form)
            .format(movie.runningTime)
    }
}
