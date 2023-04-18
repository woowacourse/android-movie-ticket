package com.woowacourse.domain

class TicketBundle private constructor(private val tickets: List<Ticket>) {

    fun calculateTotalPrice(date: String, time: String): Int = tickets.sumOf {
        it.getTicketPrice(date, time)
    }

    companion object {
        fun create(count: Int): TicketBundle {
            return TicketBundle(List(count) { Ticket() })
        }
    }
}
