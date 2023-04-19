package woowacourse.movie

import woowacourse.movie.ticket.Ticket

data class Reservation(
    val tickets: List<Ticket>,
    val paymentType: PaymentType = PaymentType.OFFLINE,
) {
    val payment: Int get() = tickets.sumOf { it.price }
    val movieId: Long get() = tickets[0].movieId
}
