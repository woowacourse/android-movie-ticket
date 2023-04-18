package woowacourse.movie.domain

import woowacourse.movie.ui.model.MovieModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun mapToMovie(movie: MovieModel): Movie {
    return Movie(
        movie.title,
        movie.startDate,
        movie.endDate,
        movie.runningTime,
        movie.description
    )
}

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
