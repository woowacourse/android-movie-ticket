package woowacourse.movie.model

import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class MovieReservation(
    val id: Long,
    val movie: Movie,
    val reserveSeats: ReserveSeats,
    val screenDateTime: LocalDateTime,
    val headCount: HeadCount,
    val cancelDeadLine: Duration = 15.minutes,
) {
    constructor(
        id: Long,
        screeningMovie: ScreeningMovie,
        screenDateTime: LocalDateTime,
        reserveSeats: ReserveSeats,
        headCount: HeadCount,
        cancelDeadLine: Duration = 15.minutes,
    ) : this(
        id,
        screeningMovie.movie,
        reserveSeats,
        screenDateTime,
        headCount = headCount,
        cancelDeadLine = cancelDeadLine,
    )

    val totalPrice: Price get() = reserveSeats.totalPrice
}
