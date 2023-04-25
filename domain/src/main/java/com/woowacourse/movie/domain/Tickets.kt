package com.woowacourse.movie.domain

import java.time.LocalDateTime

class Tickets(tickets: Set<Ticket>) {
    private val _tickets = tickets.toMutableSet()
    val tickets: Set<Ticket>
        get() = _tickets.toSortedSet()

    val size: Int
        get() = _tickets.size

    init {
        require(size >= MIN_TICKET_COUNT) { INVALID_TICKET_COUNT_EXCEPTION_MESSAGE }
    }

    fun addOrRemoveTicket(ticket: Ticket) {
        if (find(ticket) != null) {
            removeTicket(ticket)
        } else {
            addTicket(ticket)
        }
    }

    private fun addTicket(ticket: Ticket) = _tickets.add(ticket)

    private fun removeTicket(ticket: Ticket) = _tickets.remove(ticket)

    fun find(ticket: Ticket): Ticket? = _tickets.find { it == ticket }

    fun isEmpty(): Boolean = _tickets.isEmpty()

    fun getTotalPrice(currentDateTime: LocalDateTime): Int {
        var money = 0
        tickets.forEach {
            money += it.calculatePrice(it.rank, currentDateTime)
        }
        return money
    }

    companion object {
        private const val MIN_TICKET_COUNT = 0
        private const val INVALID_TICKET_COUNT_EXCEPTION_MESSAGE = "티켓 개수는 음수 일 수 없습니다."
    }
}
