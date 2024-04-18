package woowacourse.movie.repository

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.ScreeningMovie
import java.time.LocalDateTime

interface MovieRepository {
    fun screenMovies(): List<ScreeningMovie>

    fun screenMovieById(id: Long): ScreeningMovie

    fun reserveMovie(
        id: Long,
        dateTime: LocalDateTime,
        count: HeadCount,
    ): Result<Unit>

    fun movieReservationById(id: Long): MovieReservation
}
