package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MovieDate(private val startLocalDate: LocalDate, private val endLocalDate: LocalDate) {
    fun formatDateRange(): String {
        val startDate =
            startLocalDate.format(DateTimeFormatter.ofPattern("yyyy.M.d", Locale.getDefault()))
        val endDate =
            endLocalDate.format(DateTimeFormatter.ofPattern("yyyy.M.d", Locale.getDefault()))
        return "$startDate ~ $endDate"
    }

    fun generateDates(): List<String> {
        val dates = mutableListOf<String>()
        var currentDate = startLocalDate
        while (currentDate <= endLocalDate) {
            val formattedDate =
                currentDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.getDefault()))
            dates.add(formattedDate)
            currentDate = currentDate.plusDays(1)
        }
        return dates
    }

    companion object {
        fun isWeekend(localDate: LocalDate): Boolean {
            val day = localDate.dayOfWeek
            return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY
        }
    }
}
