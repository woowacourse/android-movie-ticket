package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate

enum class DayType {
    WEEKDAY,
    WEEKEND,
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
