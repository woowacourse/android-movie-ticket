package woowacourse.movie.domain.ticket

import woowacourse.movie.domain.seat.Seat
import java.io.Serializable
import java.time.LocalDateTime

data class MovieTicket(
    val movieId: Int,
    val screeningDateTime: LocalDateTime,
    val headCount: Int,
    var amount: Int,
    val selectedSeats: List<Seat>,
) : Serializable
