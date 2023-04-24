package woowacourse.movie.view

import android.os.Bundle
import woowacourse.movie.view.model.MovieUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieDateTimePicker(
    private val dateSpinner: DateSpinner,
    private val timeSpinner: TimeSpinner,
) {

    fun makeView(movieUiModel: MovieUiModel, savedInstanceState: Bundle?) {
        dateSpinner.make(savedInstanceState, movieUiModel, timeSpinner)
    }

    fun save(outState: Bundle) {
        dateSpinner.save(outState)
        timeSpinner.save(outState)
    }

    fun getSelectedDateTime(): LocalDateTime {
        val date = getSelectedDate()
        val time = getSelectedTime()
        return LocalDateTime.of(date, time)
    }

    private fun getSelectedDate(): LocalDate {
        return dateSpinner.getSelectedDate()
    }

    private fun getSelectedTime(): LocalTime {
        return timeSpinner.getSelectedTime()
    }
}
