package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate

class MovieDate(val startLocalDate: LocalDate, val endLocalDate: LocalDate) {
    init {
        require(startLocalDate <= endLocalDate) { INVALID_DATE_RANGE_MESSAGE }
    }

    fun generateDates(): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var currentDate = startLocalDate
        while (currentDate <= endLocalDate) {
            dates.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }
        return dates
    }

    companion object {
        fun isWeekend(localDate: LocalDate): Boolean {
            val day = localDate.dayOfWeek
            return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY
        }

        private const val INVALID_DATE_RANGE_MESSAGE = "영화 상영 날짜의 시작일은 종료일보다 이전이어야 합니다."
    }
}
