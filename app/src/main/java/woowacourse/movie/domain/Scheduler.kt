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
        val dayType = DayType.of(selectedDate)
        val showTimes =
            (dayType.openingHour until dayType.closingHour step dayType.interval).map { hour ->
                LocalTime.of(hour, 0)
            }
        return if (isToday) {
            showTimes.filter { time -> time.hour > now.hour }
        } else {
            showTimes
        }
    }
}
