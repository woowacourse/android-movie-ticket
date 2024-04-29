package woowacourse.movie.model.movie

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ScreeningTimes(
    val weekDay: List<LocalTime>,
    val weekEnd: List<LocalTime>,
) {
    fun loadScheduleByDateType(selectedDate: String): List<LocalTime> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(selectedDate, formatter)
        val dayOfWeek = date.dayOfWeek

        return when (dayOfWeek) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> weekEnd
            else -> weekDay
        }
    }
}
