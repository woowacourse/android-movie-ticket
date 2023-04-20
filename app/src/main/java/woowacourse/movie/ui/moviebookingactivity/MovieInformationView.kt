package woowacourse.movie.ui.moviebookingactivity

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.util.getString

class MovieInformationView(private val view: ViewGroup, movieData: MovieUIModel) {

    init {
        initMovieInformation(movieData)
    }

    private fun initMovieInformation(movieData: MovieUIModel) {
        val ivBookingPoster = view.findViewById<ImageView>(R.id.iv_booking_poster)
        val tvBookingMovieName = view.findViewById<TextView>(R.id.tv_booking_movie_name)
        val tvBookingScreeningDay = view.findViewById<TextView>(R.id.tv_booking_screening_day)
        val tvBookingRunningTime = view.findViewById<TextView>(R.id.tv_booking_running_time)
        val tvBookingDescription = view.findViewById<TextView>(R.id.tv_booking_description)

        ivBookingPoster.setImageResource(movieData.posterImage)
        tvBookingMovieName.text = movieData.title
        tvBookingScreeningDay.text = tvBookingScreeningDay.getString(R.string.screening_date_format)
            .format(
                movieData.screeningStartDay.format(DateTimeFormatters.hyphenDateFormatter),
                movieData.screeningEndDay.format(DateTimeFormatters.hyphenDateFormatter)
            )
        tvBookingRunningTime.text = tvBookingRunningTime.getString(R.string.running_time_format)
            .format(movieData.runningTime)
        tvBookingDescription.text = movieData.description
    }
}
