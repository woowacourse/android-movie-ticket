package woowacourse.movie.domain.schedule

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Scheduler {
    fun getScreeningDates(
        startDate: LocalDate,
        endDate: LocalDate,
        now: LocalDateTime,
    ): List<LocalDate> {
        val today = now.toLocalDate()
        val earliestDate =
            when {
                startDate.isAfter(today) -> startDate
                getShowtimes(today, now).isNotEmpty() -> today
                else -> today.plusDays(1)
            }
        return generateSequence(earliestDate) { date ->
            if (date.isBefore(endDate)) date.plusDays(1) else null
        }.toList()
    }

    fun getShowtimes(
        selectedDate: LocalDate,
        now: LocalDateTime,
    ): List<LocalTime> {
        val openingHour =
            when (DayType.of(selectedDate)) {
                DayType.WEEKDAY -> WEEKDAY_OPENING_HOUR
                DayType.WEEKEND -> WEEKEND_OPENING_HOUR
            }
        val showtimes: List<LocalTime> =
            (openingHour until CLOSING_HOUR step SHOWTIME_INTERVAL_HOUR).map { hour ->
                LocalTime.of(hour, 0)
            }
        return if (selectedDate == now.toLocalDate()) {
            showtimes.filter { showtime -> showtime.hour > now.hour }
        } else {
            showtimes
        }
    }

    companion object {
        private const val WEEKDAY_OPENING_HOUR = 10
        private const val WEEKEND_OPENING_HOUR = 9
        private const val CLOSING_HOUR = 24
        private const val SHOWTIME_INTERVAL_HOUR = 2
    }
}
