package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTime(
    private val date: LocalDate,
    private val currentDateTime: LocalDateTime,
) {
    fun selectableTimes(): List<LocalTime> {
        val endHour = LocalTime.of(END_HOUR, DEFAULT_MINUTE)
        val startHour = createLocalTime()

        val screeningTime =
            generateSequence(startHour) {
                if (it in startHour..<endHour) it.plusHours(HOURS_INTERVAL) else null
            }.toList()

        if (LocalDate.of(date.year, date.month, date.dayOfMonth) == currentDateTime.toLocalDate()) {
            return screeningTime.filter { it > currentDateTime.toLocalTime() }
        }

        return screeningTime
    }

    private fun createLocalTime(): LocalTime {
        if (isWeekend()) {
            return LocalTime.of(WEEKEND_START_HOUR, DEFAULT_MINUTE)
        }
        return LocalTime.of(WEEKDAY_START_HOUR, DEFAULT_MINUTE)
    }

    private fun isWeekend(): Boolean {
        return date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
    }

    companion object {
        private const val HOURS_INTERVAL = 2L
        private const val WEEKEND_START_HOUR = 9
        private const val WEEKDAY_START_HOUR = 10
        private const val END_HOUR = 22
        private const val DEFAULT_MINUTE = 0
    }
}
