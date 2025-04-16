package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate

enum class DayType {
    WEEKDAY, WEEKEND;

    companion object {
        fun from(date: LocalDate): DayType {
            val dayOfWeek = date.dayOfWeek
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                return WEEKEND
            }
            return WEEKDAY
        }
    }
}