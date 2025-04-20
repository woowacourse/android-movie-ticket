package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

class Screening(
    movie: Movie,
    val period: ClosedRange<LocalDate>,
    private val showTimePolicy: ShowtimePolicy = DefaultShowtimePolicy(),
) {
    val movieId: String = movie.movieId
    val title: String = movie.title
    val runningTime: Int = movie.runningTime

    fun showtimes(date: LocalDate): List<LocalTime> = showTimePolicy.showtimes(date)
}
