package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Scheduler {
    fun getScreeningDates(
        startDate: LocalDate,
        endDate: LocalDate,
        today: LocalDate,
    ): List<LocalDate> {
        val earliestDate = if (startDate.isBefore(today)) today else startDate
        var currentDate = earliestDate
        val dates = mutableListOf<LocalDate>()
        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }
        return dates
    }

    fun getShowTimes(
        selectedDate: LocalDate,
        now: LocalDateTime,
    ): List<LocalTime> {
        val isToday = selectedDate == now.toLocalDate()
        val showTimes = if (selectedDate.isWeekend()) WEEKEND_SHOW_TIMES else WEEKDAY_SHOW_TIMES
        return if (isToday) {
            showTimes.filter { time -> time.hour > now.hour }
        } else {
            showTimes
        }
    }

    private fun LocalDate.isWeekend(): Boolean {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
    }

    companion object {
        private const val WEEKEND_OPENING_HOUR = 9
        private const val WEEKDAY_OPENING_HOUR = 10
        private const val CLOSING_HOUR = 24
        private const val SCREENING_TIME_INTERVAL = 2

        private val WEEKEND_SHOW_TIMES =
            (WEEKEND_OPENING_HOUR until CLOSING_HOUR step SCREENING_TIME_INTERVAL).map { hour ->
                LocalTime.of(hour, 0)
            }
        private val WEEKDAY_SHOW_TIMES =
            (WEEKDAY_OPENING_HOUR until CLOSING_HOUR step SCREENING_TIME_INTERVAL).map { hour ->
                LocalTime.of(hour, 0)
            }
    }
}
