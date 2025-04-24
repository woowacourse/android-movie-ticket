package woowacourse.movie.domain.model

class TicketMachine {
    fun tickets(ticketTypes: List<TicketType>): Tickets {
        return Tickets(
            ticketTypes.map { ticketType ->
                createTicket(ticketType)
            },
        )
    }

    private fun createTicket(ticketType: TicketType): Ticket {
        val ticketPrice = ticketPrice(ticketType)
        return Ticket(ticketPrice)
    }

    private fun ticketPrice(ticketType: TicketType): Price {
        return when (ticketType) {
            TicketType.DEFAULT -> Price(DEFAULT_TICKET_PRICE)
        }
    }

    companion object {
        private const val DEFAULT_TICKET_PRICE = 1_3000
    }
}
