package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.policy.DiscountDecorator

class Tickets(tickets: List<Ticket>, val reservation: Reservation) {
    private val _tickets = tickets.toMutableList()
    val tickets: List<Ticket>
        get() = _tickets.toList()

    val size: Int
        get() = _tickets.size

    init {
        require(size >= MIN_TICKET_COUNT) { INVALID_TICKET_COUNT_EXCEPTION_MESSAGE }
    }

    fun addTicket(ticket: Ticket) {
        if (_tickets.find { it == ticket } == null)
            _tickets.add(ticket)
    }

    fun removeTicket(ticket: Ticket) {
        _tickets.remove(ticket)
    }

    fun calculatePrice(decorator: DiscountDecorator): Int {
        var money = 0
        tickets.forEach {
            money += decorator.calculatePrice(it.rank)
        }
        return money
    }

    companion object {
        private const val MIN_TICKET_COUNT = 1
        private const val INVALID_TICKET_COUNT_EXCEPTION_MESSAGE = "티켓 개수는 최소 1장 이상이어야 합니다."
    }
}
