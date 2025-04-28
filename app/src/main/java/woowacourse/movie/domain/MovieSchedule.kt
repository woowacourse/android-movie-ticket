package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieSchedule(
    private val startDate: LocalDate,
    private val endDate: LocalDate,
) {
    private val dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
    private var selectedDate = ""
    private var selectedTime = ""

    fun setDate(date: String) {
        selectedDate = date
    }

    fun setTime(time: String) {
        selectedTime = time
    }

    fun getDate(): String = selectedDate

    fun getTime(): String = selectedTime

    fun getDates(): List<String> {
        val dates = mutableListOf<String>()
        var current = startDate
        while (!current.isAfter(endDate)) {
            dates.add(current.format(dateFormatter))
            current = current.plusDays(1)
        }
        return dates
    }

    fun getTimes(date: String): List<String> {
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

    companion object {
        private const val DATE_PATTERN = "yyyy.M.d"

        private const val WEEKEND_START_HOUR = 9
        private const val WEEKDAY_START_HOUR = 10
        private const val LAST_HOUR = 24
        private const val HOUR_STEP = 2
    }
}
