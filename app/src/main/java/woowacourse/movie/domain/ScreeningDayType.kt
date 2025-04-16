package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

enum class ScreeningDayType(val startTime: LocalTime) {
    WEEKDAY(LocalTime.of(10, 0)),
    WEEKEND(LocalTime.of(9, 0)),
    ;

    companion object {
        fun of(date: LocalDate): ScreeningDayType {
            return if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) {
                WEEKEND
            } else {
                WEEKDAY
            }
        }
    }
}
