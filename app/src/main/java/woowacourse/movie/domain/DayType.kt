package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate

enum class DayType(
    val openingHour: Int,
    val closingHour: Int,
    val interval: Int,
) {
    WEEKDAY(10, 24, 2),
    WEEKEND(9, 24, 2),
    ;

    companion object {
        fun of(date: LocalDate): DayType {
            return when (date.dayOfWeek) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> WEEKEND
                else -> WEEKDAY
            }
        }
    }
}
