package woowacourse.movie.domain

import woowacourse.movie.domain.seat.SeatPosition
import java.time.LocalDateTime

data class Ticket(
    val price: Int,
    val date: LocalDateTime,
    val numberOfPeople: Int,
    val seats: List<SeatPosition>
)
