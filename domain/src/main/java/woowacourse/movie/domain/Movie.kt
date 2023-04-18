package woowacourse.movie.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) {
    fun getDatesBetweenTwoDates(): List<LocalDate> {
        val numberOfDates = ChronoUnit.DAYS.between(startDate, endDate) + 1
        return generateSequence(startDate) { it.plusDays(1) }
            .take(numberOfDates.toInt())
            .toList()
    }
}
