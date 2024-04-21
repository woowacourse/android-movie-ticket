package woowacourse.movie.domain.model

data class Reservation(
    val id: Int,
    val screen: Screen,
    val ticket: Ticket,
) {
    val totalPrice: Int
        get() = ticket.count * screen.price
}

data class Reservation2(
    val id: Int,
    val screen: Screen2,
    val ticket: Ticket,
) {
    val totalPrice: Int
        get() = ticket.count * screen.price
}
