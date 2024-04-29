package woowacourse.movie.data

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.board.SeatBoard
import woowacourse.movie.model.board.Seats
import woowacourse.movie.model.date.ScreeningMovie
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDateTime

class FakeMovieRepository : MovieRepository {
    private val screenMovies: List<ScreeningMovie> = ScreeningMovie.stubMovies()
    private var reservations: List<MovieReservation> = emptyList()
    private var reservationId: Long = 0

    override fun screenSeats(
        screenMovieId: Long,
        headCount: Int,
        dateTime: LocalDateTime,
    ): Result<SeatBoard> {
        return runCatching { SeatBoard.STUB.copy(headCount = headCount.let(::HeadCount)) }
    }

    override fun screenMovies(): List<ScreeningMovie> = screenMovies

    override fun screenMovieById(id: Long): Result<ScreeningMovie> {
        return runCatching {
            screenMovies.find { it.id == id } ?: error(
                "$id : id에 해당 하는 영화가 없습니다.",
            )
        }
    }

    override fun reserveMovie(
        id: Long,
        dateTime: LocalDateTime,
        count: HeadCount,
        selectedSeats: Seats,
    ): Result<Long> {
        return runCatching {
            reservations +=
                MovieReservation(
                    id = ++reservationId,
                    screeningMovie = screenMovieById(id).getOrThrow(),
                    screenDateTime = dateTime,
                    headCount = count,
                    seats = selectedSeats,
                )
            reservationId
        }
    }

    override fun movieReservationById(id: Long): MovieReservation? {
        return reservations.find { it.id == id }
    }
}
