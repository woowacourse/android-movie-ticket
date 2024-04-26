package woowacourse.movie.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningSchedule(
    private var date: LocalDate? = null,
    private var time: LocalTime? = null,
) {
    val dateTime: LocalDateTime?
        get() = if (isValidate()) LocalDateTime.of(date, time) else null

    fun updateDate(newDate: LocalDate?) {
        date = newDate
    }

    fun updateTime(newTime: LocalTime) {
        time = newTime
    }

    fun times(date: LocalDate?): List<LocalTime> =
        when (date?.dayOfWeek) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> weekendTimes
            else -> weekdayTimes
        }

    fun isValidate(): Boolean = date != null && time != null

    companion object {
        val weekdayTimes =
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

        val weekendTimes =
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
    }
}
