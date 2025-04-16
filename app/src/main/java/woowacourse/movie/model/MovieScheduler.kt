package woowacourse.movie.model

import java.time.LocalDate
import java.time.LocalTime

class MovieScheduler(
    private val startDate: LocalDate,
    private val endDate: LocalDate,
) {
    fun getBookableDates(now: LocalDate = LocalDate.now()): List<LocalDate> {
        val bookableDates = mutableListOf<LocalDate>()
        var current = startDate

        while (!current.isAfter(endDate)) {
            if (!current.isBefore(now)) {
                bookableDates.add(current)
            }
            current = current.plusDays(DAYS_TO_ADD)
        }

        return bookableDates
    }

    fun getBookableTimes(
        date: LocalDate,
        now: LocalTime = LocalTime.now(),
    ): List<LocalTime> {
        val dayType = DayType.from(date)
        val startHour = if (dayType == DayType.WEEKEND) WEEKEND_START_TIME else WEEKDAY_START_TIME

        val times = mutableListOf<LocalTime>()
        var time = LocalTime.of(startHour, START_MINUTE)

        while (time != LocalTime.MIDNIGHT) {

            if (time.isAfter(now)) {
                times.add(time)
            }

            time = time.plusHours(HOURS_TO_ADD)
        }

        return times
    }

    companion object {
        private const val WEEKDAY_START_TIME = 10
        private const val WEEKEND_START_TIME = 9
        private const val DAYS_TO_ADD = 1L
        private const val HOURS_TO_ADD = 2L
        private const val START_MINUTE = 0
    }
}