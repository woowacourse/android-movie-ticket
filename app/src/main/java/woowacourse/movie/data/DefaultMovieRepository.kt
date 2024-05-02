package woowacourse.movie.data

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.board.SeatBoard
import woowacourse.movie.model.board.Seats
import woowacourse.movie.model.date.ScreeningMovie
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDateTime

object DefaultMovieRepository : MovieRepository {
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

    override fun screenMovieById(id: Long): ScreeningMovie? {
        return screenMovies.find { it.id == id }
    }

    override fun reserveMovie(
        id: Long,
        dateTime: LocalDateTime,
        count: HeadCount,
        selectedSeats: Seats,
    ): Result<Long> {
        return runCatching {
            val screeningMovie = screenMovieById(id) ?: error("id: $id 에 해당하는 영화가 없습니다.")
            reservations +=
                MovieReservation(
                    id = ++reservationId,
                    screeningMovie = screeningMovie,
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
