package woowacourse.movie.domain

import SeatPosition
import java.time.LocalDateTime

data class Ticket(
    val price: Int,
    val date: LocalDateTime,
    val numberOfPeople: Int,
    val seats: List<SeatPosition>
)
