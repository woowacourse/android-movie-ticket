package woowacourse.movie.domain.rules

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface RunningTimeRule {
    fun whenWeekDay(): List<LocalTime>

    fun whenWeekEnd(): List<LocalTime>

    fun whenTargetDay(
        targetDay: LocalDate,
        now: LocalDateTime,
    ): List<LocalTime> {
        val dayOfWeek = targetDay.dayOfWeek
        return if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
            whenWeekDay()
        } else {
            whenWeekEnd()
        }.filter {
            if (targetDay == now.toLocalDate()) {
                it > now.toLocalTime() || it == LocalTime.of(0, 0, 0)
            } else {
                true
            }
        }
    }
}
