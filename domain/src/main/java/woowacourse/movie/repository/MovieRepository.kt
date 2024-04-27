package woowacourse.movie.repository

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.date.ScreeningMovie
import java.time.LocalDateTime

interface MovieRepository {
    fun screenMovies(): List<ScreeningMovie>

    fun screenMovieById(id: Long): Result<ScreeningMovie>

    fun reserveMovie(
        id: Long,
        dateTime: LocalDateTime,
        count: HeadCount,
    ): Result<Long>

    fun movieReservationById(id: Long): Result<MovieReservation>
}
