package woowacourse.movie.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

enum class DayType(val startTime: LocalTime, val interval: Int) {
    WEEKDAY(LocalTime.of(10, 0), 2),
    WEEKEND(LocalTime.of(9, 0), 2),
    ;

    companion object {
        fun of(date: LocalDate): DayType {
            if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) {
                return WEEKEND
            }
            return WEEKDAY
        }
    }
}
