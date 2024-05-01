package woowacourse.movie.model

import woowacourse.movie.model.board.Seats
import woowacourse.movie.model.date.ScreeningMovie
import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class MovieReservation(
    val id: Long,
    val movie: Movie,
    val screenDateTime: LocalDateTime,
    val headCount: HeadCount,
    val cancelDeadLine: Duration = 15.minutes,
    val seats: Seats = Seats(),
) {
    constructor(
        id: Long,
        screeningMovie: ScreeningMovie,
        screenDateTime: LocalDateTime,
        headCount: HeadCount,
        cancelDeadLine: Duration = 15.minutes,
        seats: Seats,
    ) : this(
        id,
        screeningMovie.movie,
        screenDateTime,
        headCount = headCount,
        cancelDeadLine = cancelDeadLine,
        seats = seats,
    )

    val totalPrice: Price get() = seats.selectedSeats().totalPrice().let(::Price)
}
