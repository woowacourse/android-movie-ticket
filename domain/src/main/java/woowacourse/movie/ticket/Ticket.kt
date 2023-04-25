package woowacourse.movie.ticket

import woowacourse.movie.policy.TicketPriceAdapter
import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val seat: Seat,
) {
    val price get() = TicketPriceAdapter().getPayment(this)
}
