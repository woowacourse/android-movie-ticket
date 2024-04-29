package woowacourse.movie.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class ScreenDate(val date: LocalDate) {
    fun getLocalDateTime(time: LocalTime?): LocalDateTime = LocalDateTime.of(date, time)

    fun getSelectableTimes(): List<LocalTime> {
        return if (isWeekend(date)) weekendSelectableTimes else weekdaySelectableTimes
    }

    private fun isWeekend(date: LocalDate): Boolean {
        val dayOfWeek = date.dayOfWeek
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
    }

    companion object {
        val weekendSelectableTimes: List<LocalTime> =
            listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )

        val weekdaySelectableTimes: List<LocalTime> =
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
                LocalTime.of(0, 0),
            )
    }
}
