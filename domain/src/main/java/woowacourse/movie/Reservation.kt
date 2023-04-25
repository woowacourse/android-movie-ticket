package woowacourse.movie

import woowacourse.movie.ticket.Ticket
import java.time.LocalDateTime

data class Reservation(
    val tickets: Set<Ticket>,
    val paymentType: PaymentType = PaymentType.OFFLINE,
) {
    val payment: Int get() = tickets.sumOf { it.price }
    val movieId: Long get() = tickets.first().movieId
    val bookedDateTime: LocalDateTime get() = tickets.first().bookedDateTime
    val count: Int get() = tickets.size
}
