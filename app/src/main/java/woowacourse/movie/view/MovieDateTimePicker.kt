package woowacourse.movie.view

import android.os.Bundle
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.LocalTime

class MovieDateTimePicker(
    val dateSpinner: DateSpinner,
    val timeSpinner: TimeSpinner,
) {

    fun makeView(movie: Movie, savedInstanceState: Bundle?) {
        dateSpinner.make(savedInstanceState, movie, timeSpinner)
    }

    fun save(outState: Bundle) {
        dateSpinner.save(outState)
        timeSpinner.save(outState)
    }

    fun getSelectedDate(): LocalDate {
        return dateSpinner.getSelectedDate()
    }

    fun getSelectedTime(): LocalTime {
        return timeSpinner.getSelectedTime()
    }
}
