package woowacourse.movie.domain.model

import java.time.LocalDate

class ScreeningInfo(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
) {
    fun isInScreeningDateRange(date: LocalDate): Boolean {
        return isEqualOrAfterStartDate(date) && isEqualOrBeforeEndDate(date)
    }

    private fun isEqualOrAfterStartDate(date: LocalDate): Boolean {
        return startDate.isEqual(date) || startDate.isBefore(date)
    }

    private fun isEqualOrBeforeEndDate(date: LocalDate): Boolean {
        return endDate.isEqual(date) || endDate.isAfter(date)
    }
}
