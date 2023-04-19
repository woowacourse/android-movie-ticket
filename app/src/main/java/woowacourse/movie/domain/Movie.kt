package woowacourse.movie.domain

import androidx.annotation.DrawableRes
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Movie(
    @DrawableRes
    val poster: Int,
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
