package woowacourse.movie.domain.model

import java.time.LocalDateTime

data class Reservation(
    val id: Int,
    val screen: Screen,
    val ticket: Ticket,
    val seats: List<Seat>,
    val dateTime: LocalDateTime,
) {
    val totalPrice: Int
        get() = ticket.count * screen.price
}
