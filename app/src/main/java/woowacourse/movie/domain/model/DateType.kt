package woowacourse.movie.domain.model

import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY
import java.time.LocalDate

enum class DateType {
    WEEKDAY,
    WEEKEND,
    ;

    companion object {
        fun from(date: LocalDate): DateType =
            when (date.dayOfWeek) {
                SATURDAY -> WEEKEND
                SUNDAY -> WEEKEND
                else -> WEEKDAY
            }
    }
}
