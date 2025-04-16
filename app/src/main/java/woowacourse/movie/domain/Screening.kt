package woowacourse.movie.domain

import androidx.annotation.DrawableRes
import java.time.LocalDate
import java.time.LocalTime

data class Screening(
    private val movie: Movie,
    val period: ClosedRange<LocalDate>,
) {
    private val showTimePolicy = ShowtimePolicy()

    @DrawableRes
    val posterId: Int = movie.posterId
    val title: String = movie.title
    val runningTime: Int = movie.runningTime

    fun showtimes(date: LocalDate): List<LocalTime> = showTimePolicy.showtimes(date)
}
