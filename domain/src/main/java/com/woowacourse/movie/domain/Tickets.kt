package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.policy.DiscountDecorator

class Tickets(tickets: Set<Ticket>, val reservation: Reservation) {
    private val _tickets = tickets.toMutableSet()
    val tickets: Set<Ticket>
        get() = _tickets.toSet()

    val size: Int
        get() = _tickets.size

    init {
        require(size >= MIN_TICKET_COUNT) { INVALID_TICKET_COUNT_EXCEPTION_MESSAGE }
    }

    fun addTicket(ticket: Ticket): Boolean = _tickets.add(ticket)

    fun removeTicket(ticket: Ticket): Boolean = _tickets.remove(ticket)

    fun find(ticket: Ticket): Ticket? = _tickets.find { it == ticket }

    fun isEmpty(): Boolean = _tickets.isEmpty()

    fun calculatePrice(decorator: DiscountDecorator): Int {
        var money = 0
        tickets.forEach {
            money += decorator.calculatePrice(it.rank)
        }
        return money
    }

    companion object {
        private const val MIN_TICKET_COUNT = 0
        private const val INVALID_TICKET_COUNT_EXCEPTION_MESSAGE = "티켓 개수는 음수 일 수 없습니다."
    }
}
