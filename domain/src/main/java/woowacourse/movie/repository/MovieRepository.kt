package woowacourse.movie.repository

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.board.SeatBoard
import woowacourse.movie.model.board.Seats
import woowacourse.movie.model.date.ScreeningMovie
import java.time.LocalDateTime

interface MovieRepository {
    fun screenSeats(
        screenMovieId: Long,
        headCount: Int,
        dateTime: LocalDateTime,
    ): Result<SeatBoard>

    fun screenMovies(): List<ScreeningMovie>

    fun screenMovieById(id: Long): Result<ScreeningMovie>

    fun reserveMovie(
        id: Long,
        dateTime: LocalDateTime,
        count: HeadCount,
        selectedSeats: Seats,
    ): Result<Long>

    fun movieReservationById(id: Long): Result<MovieReservation>
}
