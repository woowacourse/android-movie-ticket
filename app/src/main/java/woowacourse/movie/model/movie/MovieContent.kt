package woowacourse.movie.model.movie

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class MovieContent(
    val imageId: String,
    val title: String,
    val openingMovieDate: LocalDate,
    val endingMoviesDate: LocalDate,
    val runningTime: Int,
    val synopsis: String,
    val id: Long = 0,
) {
    fun getDatesInRange(): List<LocalDate> {
        val numberOfDays = ChronoUnit.DAYS.between(openingMovieDate, endingMoviesDate) + OFFSET
        return List(numberOfDays.toInt()) { index -> openingMovieDate.plusDays(index.toLong()) }
    }

    companion object {
        private const val OFFSET = 1
    }
}
