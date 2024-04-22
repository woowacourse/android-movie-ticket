package woowacourse.movie.domain.model

data class Reservation(
    val id: Int,
    val screen: IScreen,
    val ticket: Ticket,
) {
    val totalPrice: Int
        get() = ticket.count * screen.price
}
