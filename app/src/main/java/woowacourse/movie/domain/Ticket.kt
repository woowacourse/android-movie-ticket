package woowacourse.movie.domain

import java.io.Serializable

data class Ticket(private val ticketType: TicketType) : Serializable {
    val price = ticketType.price

    init {
        require(ticketType.price >= 0) { VALIDATE_TICKET_PRICE }
    }

    companion object {
        private const val VALIDATE_TICKET_PRICE = "티켓의 가격은 0원 이상입니다. "
    }
}
