package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTime(
    private val date: LocalDateTime,
) {
    fun runningTimeTable(): List<LocalTime> {
        val endHour = LocalTime.of(23, 0)
        val startHour =
            if (isWeekend()) {
                LocalTime.of(10, 0)
            } else {
                LocalTime.of(9, 0)
            }

        var currentTime = LocalTime.of(date.hour, date.minute)

        if (LocalDate.of(date.year, date.month, date.dayOfMonth) == LocalDate.now()) {
            currentTime = LocalTime.now()
        }

        val screeningTime =
            generateSequence(startHour) {
                if (it in startHour..<endHour) it.plusHours(2) else null
            }.toList()

        return screeningTime.filter { it > currentTime }
    }

    private fun isWeekend(): Boolean {
        return date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
    }

    companion object {
        private const val HOURS_INTERVAL = 2
    }
}
