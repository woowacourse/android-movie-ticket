package woowacourse.movie.domain.model

import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY

enum class DateType {
    WEEKDAY,
    WEEKEND,
    ;

    companion object {
        fun from(date: MovieDate): DateType =
            when (date.value.dayOfWeek) {
                SATURDAY -> WEEKEND
                SUNDAY -> WEEKEND
                else -> WEEKDAY
            }
    }
}
