package woowacourse.movie.domain.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class ScreeningDate(
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    fun bookingDates(today: LocalDate): List<LocalDate> {
        val start = getStartDate(today)
        val days = ChronoUnit.DAYS.between(start, endDate).toInt()
        return (0..days).map { start.plusDays(it.toLong()) }
    }

    private fun getStartDate(other: LocalDate): LocalDate {
        if (startDate.isAfter(other)) {
            return startDate
        }
        return other
    }
}
