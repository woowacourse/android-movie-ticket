package woowacourse.movie.domain

import java.time.LocalDate

enum class DayOfWeek {
    WEEKDAY, WEEKEND;

    companion object {
        fun checkDayOfWeek(date: LocalDate): DayOfWeek {
            if (date.dayOfWeek.value in (1..5)) return WEEKDAY
            return WEEKEND
        }
    }
}
