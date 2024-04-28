package woowacourse.movie.model.schedule

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class ScreeningDate(val date: LocalDate) {
    fun isWeekend(): Boolean {
        return date.dayOfWeek == DayOfWeek.SUNDAY || date.dayOfWeek == DayOfWeek.SATURDAY
    }

    fun isWeekday(): Boolean {
        return !isWeekend()
    }

    operator fun rangeTo(other: ScreeningDate): List<ScreeningDate> {
        val startDate = this.date
        val endDate = other.date
        val days = startDate.until(endDate, ChronoUnit.DAYS).toInt()
        return generateSequence(startDate) {
            it.plusDays(1)
        }.map { ScreeningDate(it) }.take(days + 1).toList()
    }
}
