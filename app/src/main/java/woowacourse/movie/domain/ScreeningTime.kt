package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTime(
    private val date: LocalDateTime,
) {
    fun runningTimeTable(): List<LocalTime> {
        val endHour = LocalTime.of(END_HOUR, DEFAULT_MINUTE)
        val startHour =
            if (isWeekend()) {
                LocalTime.of(WEEKEND_START_HOUR, DEFAULT_MINUTE)
            } else {
                LocalTime.of(WEEKDAY_START_HOUR, DEFAULT_MINUTE)
            }

        var currentTime = LocalTime.of(date.hour, date.minute)

        if (LocalDate.of(date.year, date.month, date.dayOfMonth) == LocalDate.now()) {
            currentTime = LocalTime.now()
        }

        val screeningTime =
            generateSequence(startHour) {
                if (it in startHour..<endHour) it.plusHours(HOURS_INTERVAL) else null
            }.toList()

        return screeningTime.filter { it > currentTime }
    }

    private fun isWeekend(): Boolean {
        return date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
    }

    companion object {
        private const val HOURS_INTERVAL = 2L
        private const val WEEKEND_START_HOUR = 9
        private const val WEEKDAY_START_HOUR = 10
        private const val END_HOUR = 23
        private const val DEFAULT_MINUTE = 0
    }
}
