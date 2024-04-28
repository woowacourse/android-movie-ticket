package woowacourse.movie.domain.movie

import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY
import java.time.LocalDate

class ScreenTime(private val date: LocalDate) {
    fun timeList(): List<String> {
        return when (date.dayOfWeek) {
            SATURDAY -> weekendTimeList
            SUNDAY -> weekendTimeList
            else -> weekDayTimeList
        }
    }

    companion object {
        val weekDayTimeList =
            listOf("10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00", "24:00")
        val weekendTimeList =
            listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")
    }
}
