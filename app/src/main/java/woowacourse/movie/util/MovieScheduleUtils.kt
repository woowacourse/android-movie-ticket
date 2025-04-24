package woowacourse.movie.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object MovieScheduleUtils {
    private const val DATE_PATTERN = "yyyy.M.d"

    private const val WEEKEND_START_HOUR = 9
    private const val WEEKDAY_START_HOUR = 10
    private const val LAST_HOUR = 24
    private const val HOUR_STEP = 2

    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

    fun generateScreeningDates(
        startDate: String,
        endDate: String,
    ): List<String> {
        val parsedStartDate = LocalDate.parse(startDate, dateFormatter)
        val parsedEndDate = LocalDate.parse(endDate, dateFormatter)

        val dates = mutableListOf<String>()
        var current = parsedStartDate

        while (!current.isAfter(parsedEndDate)) {
            dates.add(current.format(dateFormatter))
            current = current.plusDays(1)
        }

        return dates
    }

    fun generateScreeningTimesFor(date: String): List<String> {
        val parsedDate = LocalDate.parse(date, dateFormatter)

        val startHour =
            when (parsedDate.dayOfWeek) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> WEEKEND_START_HOUR
                else -> WEEKDAY_START_HOUR
            }

        return (startHour..LAST_HOUR step HOUR_STEP).map { hour ->
            String.format("%02d:00", hour)
        }
    }
}
