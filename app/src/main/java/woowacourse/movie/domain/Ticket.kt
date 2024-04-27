package woowacourse.movie.domain

import woowacourse.movie.domain.seat.Seat
import java.time.LocalDateTime

data class Ticket(
    val id: Long,
    val movie: Movie,
    val schedule: LocalDateTime,
    val seats: List<Seat>,
    val price: Long,
)
