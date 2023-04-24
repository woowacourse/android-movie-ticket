package com.woowacourse.domain.ticket

class TicketBundle(val count: Int, private val tickets: List<Ticket> = emptyList()) {

    fun add(ticket: Ticket) = TicketBundle(count, tickets + ticket)

    fun remove(ticket: Ticket) = TicketBundle(count, tickets - ticket)

    fun calculateTotalPrice(date: String, time: String): Int = tickets.sumOf {
        it.getCalculatedTicketPrice(date, time)
    }
}
