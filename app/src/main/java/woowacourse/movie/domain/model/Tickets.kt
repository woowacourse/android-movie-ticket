package woowacourse.movie.domain.model

import java.io.Serializable

class Tickets(private val tickets: List<Ticket>) : Serializable {
    val count get() = tickets.size

    fun isEmpty() = tickets.isEmpty()

    fun addTicket(ticket: Ticket): Tickets = Tickets(tickets + ticket)

    fun removeTicket(ticket: Ticket): Tickets = Tickets(tickets.filterNot { it.seat == ticket.seat })

    fun totalPrice() = tickets.sumOf { it.price }
}
