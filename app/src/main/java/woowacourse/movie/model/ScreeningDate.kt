package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate

data class ScreeningDate(val date: LocalDate) {
    fun nextDay(): ScreeningDate {
        return ScreeningDate(date.plusDays(SCREENING_DATE_INTERVAL))
    }

    private fun isWeekend(): Boolean {
        return date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
    }

    companion object {
        private const val WEEKEND_START_HOUR = 9
        private const val WEEKDAY_START_HOUR = 10
        private const val END_HOUR = 24
        private const val SCREENING_HOUR_INTERVAL = 2
        private const val SCREENING_DATE_INTERVAL = 1L

        private val YEAR_RANGE = 2000..2100
        private val MONTH_RANGE = 1..12
        private val DAY_RANGE = 1..31
        private val INVALID_YEAR_MESSAGE = "${YEAR_RANGE.first}~${YEAR_RANGE.last} 범위의 연도여야 합니다."
        private val INVALID_MONTH_MESSAGE = "${MONTH_RANGE.first}~${MONTH_RANGE.last} 범위의 월이어야 합니다."
        private val INVALID_DAY_MESSAGE = "${DAY_RANGE.first}~${DAY_RANGE.last} 범위의 일이어야 합니다."

        fun of(year: Int, month: Int, day: Int): ScreeningDate {
            require(year in YEAR_RANGE) { INVALID_YEAR_MESSAGE }
            require(month in MONTH_RANGE) { INVALID_MONTH_MESSAGE }
            require(day in DAY_RANGE) { INVALID_DAY_MESSAGE }

            return ScreeningDate(LocalDate.of(year, month, day))
        }
    }
}
