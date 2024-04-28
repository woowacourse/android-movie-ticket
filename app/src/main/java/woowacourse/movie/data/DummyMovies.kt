package woowacourse.movie.data

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.ReserveSeats
import woowacourse.movie.model.ScreeningMovie
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDateTime

object DummyMovies : MovieRepository {
    private val screenMovies: List<ScreeningMovie> =
        listOf(
            ScreeningMovie.STUB,
            ScreeningMovie.STUB,
            ScreeningMovie.STUB,
            ScreeningMovie.STUB,
        )
    private var reservations: List<MovieReservation> = emptyList()
    private var reservationId: Long = 0

    override fun screenMovies(): List<ScreeningMovie> = screenMovies

    override fun screenMovieById(id: Long): ScreeningMovie {
        return screenMovies.firstOrNull { it.id == id } ?: error(
            IdError.NO_MOVIE.message.format(id),
        )
    }

    override fun reserveMovie(
        id: Long,
        dateTime: LocalDateTime,
        count: HeadCount,
        seats: ReserveSeats,
    ): Long {
        reservations +=
            MovieReservation(
                id = ++reservationId,
                screeningMovie = screenMovieById(id),
                screenDateTime = dateTime,
                headCount = count,
                reserveSeats = seats,
            )
        return reservationId
    }

    override fun movieReservationById(id: Long): MovieReservation {
        return reservations.find { it.id == id } ?: error(
            IdError.NO_RESERVATION.message.format(id),
        )
    }
}
