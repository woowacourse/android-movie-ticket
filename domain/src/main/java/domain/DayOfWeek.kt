package domain

import java.time.LocalDate

enum class DayOfWeek {
    WEEKDAY, WEEKEND;

    companion object {
        private val daysNumber = (1..5)
        fun checkDayOfWeek(date: LocalDate): DayOfWeek {
            if (date.dayOfWeek.value in daysNumber) return WEEKDAY
            return WEEKEND
        }
    }
}
