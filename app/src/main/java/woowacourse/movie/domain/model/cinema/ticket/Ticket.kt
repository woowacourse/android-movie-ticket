package woowacourse.movie.domain.model.cinema.ticket

import woowacourse.movie.domain.model.cinema.screen.Seat
import java.time.LocalDateTime

class Ticket(
    val title: String,
    val reservationDateTime: LocalDateTime,
    val seat: Seat,
    val price: Int,
)
