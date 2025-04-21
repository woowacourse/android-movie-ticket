package woowacourse.movie.domain

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
        return generateSequence(earliestDate) { date -> date.plusDays(1) }
            .take(earliestDate.until(endDate).days + 1)
            .toList()
    }

    fun getShowtimes(
        selectedDate: LocalDate,
        now: LocalDateTime,
    ): List<LocalTime> {
        val dayType: DayType = DayType.of(selectedDate)
        val showtimes: List<LocalTime> =
            (dayType.openingHour until dayType.closingHour step dayType.interval).map { hour ->
                LocalTime.of(hour, 0)
            }
        return when (selectedDate == now.toLocalDate()) {
            true -> showtimes.filter { showtime -> showtime.hour > now.hour }
            else -> showtimes
        }
    }
}
