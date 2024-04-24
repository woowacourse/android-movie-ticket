package woowacourse.movie.data

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.ScreeningMovie
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDateTime

object StubMovieRepository : MovieRepository {
    private val screenMovies: List<ScreeningMovie> = listOf(ScreeningMovie.STUB)
    private var reservations: List<MovieReservation> = emptyList()
    private var reservationId: Long = 0

    override fun screenMovies(): List<ScreeningMovie> = screenMovies

    override fun screenMovieById(id: Long): ScreeningMovie {
        return screenMovies.find { it.id == id } ?: error(
            IdError.NO_MOVIE.message.format(id),
        )
    }

    override fun reserveMovie(
        id: Long,
        dateTime: LocalDateTime,
        count: HeadCount,
    ): Result<Long> {
        return runCatching {
            reservations +=
                MovieReservation(
                    id = ++reservationId,
                    screeningMovie = screenMovieById(id),
                    screenDateTime = dateTime,
                    headCount = count,
                )
            reservationId
        }
    }

    override fun movieReservationById(id: Long): MovieReservation {
        return reservations.find { it.id == id } ?: error(
            IdError.NO_RESERVATION.message.format(id),
        )
    }
}
