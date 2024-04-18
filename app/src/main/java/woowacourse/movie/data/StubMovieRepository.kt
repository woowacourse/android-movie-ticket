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
            "$id : id에 해당 하는 영화가 없습니다.",
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
                    id = reservationId++,
                    screeningMovie = screenMovieById(id),
                    screenDateTime = dateTime,
                    headCount = count,
                )
            reservationId
        }
    }

    override fun movieReservationById(id: Long): MovieReservation {
        return reservations.find { it.id == id } ?: error(
            "$id : id에 해당 하는 예약 내역이 없습니다.",
        )
    }
}
