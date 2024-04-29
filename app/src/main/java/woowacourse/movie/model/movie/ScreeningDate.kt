package woowacourse.movie.model.movie

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class ScreeningDate(val date: LocalDate) {
    fun screeningTime(): List<LocalTime> =
        if (isWeekend()) {
            ScreeningTime.weekendTimes()
        } else {
            ScreeningTime.weekdayTimes()
        }

    private fun isWeekend(): Boolean =
        when (date.dayOfWeek) {
            DayOfWeek.SUNDAY -> true
            DayOfWeek.SATURDAY -> true
            else -> false
        }
}
