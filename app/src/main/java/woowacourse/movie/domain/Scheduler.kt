package woowacourse.movie.domain

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
        return generateSequence(earliestDate) { date -> date.plusDays(1) }
            .take(earliestDate.until(endDate).days + 1)
            .toList()
    }

    fun getShowtimes(
        selectedDate: LocalDate,
        now: LocalDateTime,
    ): List<LocalTime> {
        val isToday = selectedDate == now.toLocalDate()
        val dayType = DayType.of(selectedDate)
        val showtimes =
            (dayType.openingHour until dayType.closingHour step dayType.interval).map { hour ->
                LocalTime.of(hour, 0)
            }
        return if (isToday) {
            showtimes.filter { time -> time.hour > now.hour }
        } else {
            showtimes
        }
    }
}
