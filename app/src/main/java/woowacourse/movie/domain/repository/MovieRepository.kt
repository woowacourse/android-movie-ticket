package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Movie
import java.time.LocalDate
import java.time.LocalTime

interface MovieRepository {
    fun createMovieList(): List<Movie>

    fun findMovieById(movieId: Int): Movie

    fun getScreeningDateInfo(movieId: Int): List<LocalDate>

    fun getScreeningTimeInfo(isWeekend: Boolean): List<LocalTime>
}
