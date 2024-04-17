package woowacourse.movie.model

class Tickets(private val numberOfTickets: Int) {
    val totalPrice: Int
        get() = numberOfTickets * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
