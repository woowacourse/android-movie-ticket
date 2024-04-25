package woowacourse.movie.model.movie

import java.time.DayOfWeek
import java.time.LocalDate

class ScreeningDateTime(val date: LocalDate) {
    fun screeningTime(): List<String> =
        if (isWeekend()) {
            WEEKEND_TIMES
        } else {
            WEEKDAY_TIMES
        }

    private fun isWeekend(): Boolean =
        when (date.dayOfWeek) {
            DayOfWeek.SUNDAY -> true
            DayOfWeek.SATURDAY -> true
            else -> false
        }

    companion object {
        val WEEKDAY_TIMES =
            listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")
        val WEEKEND_TIMES =
            listOf("10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00", "24:00")
    }
}
