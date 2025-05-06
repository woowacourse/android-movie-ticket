package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

object MovieScheduleGenerator {
    private const val WEEKEND_START_HOUR = 9
    private const val WEEKDAY_START_HOUR = 10
    private const val LAST_HOUR = 24
    private const val HOUR_STEP = 2

    fun generateScreeningDates(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var current = startDate

        while (!current.isAfter(endDate)) {
            dates.add(current)
            current = current.plusDays(1)
        }

        return dates
    }

    fun generateScreeningTimesFor(date: LocalDate): List<LocalTime> {
        val startHour =
            when (date.dayOfWeek) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> WEEKEND_START_HOUR
                else -> WEEKDAY_START_HOUR
            }

        return (startHour..LAST_HOUR step HOUR_STEP)
            .map { hour ->
                if (hour == 24) {
                    LocalTime.MIDNIGHT
                } else {
                    LocalTime.of(hour, 0)
                }
            }
    }
}
