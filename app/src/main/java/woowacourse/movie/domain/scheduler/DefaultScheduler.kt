package woowacourse.movie.domain.scheduler

import woowacourse.movie.domain.DayType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object DefaultScheduler : Scheduler {
    override fun getScreeningDates(
        startDate: LocalDate,
        endDate: LocalDate,
        today: LocalDate,
    ): List<LocalDate> {
        val earliestDate = if (startDate.isBefore(today)) today else startDate
        return buildList {
            var currentDate = earliestDate
            while (!currentDate.isAfter(endDate)) {
                add(currentDate)
                currentDate = currentDate.plusDays(1)
            }
        }
    }

    override fun getShowtimes(
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
