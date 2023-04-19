package woowacourse.movie.ticket

import woowacourse.movie.policy.TicketPriceAdapter
import java.io.Serializable
import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val seat: Seat,
) : Serializable {
    val price get() = TicketPriceAdapter().getPayment(this)
}
