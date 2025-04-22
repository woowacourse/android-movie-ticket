package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

interface RunningTimeRule {
    fun whenWeekDay(): List<LocalTime>

    fun whenWeekEnd(): List<LocalTime>

    fun whenTargetDay(targetDay: LocalDate): List<LocalTime> {
        val dayOfWeek = targetDay.dayOfWeek
        return if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
            whenWeekDay()
        } else {
            whenWeekEnd()
        }
    }
}
