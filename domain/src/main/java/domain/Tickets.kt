package domain

import domain.discountPolicy.DisCountPolicies
import domain.seatPolicy.SeatPolicies

class Tickets(initList: List<Ticket>) {
    private val _list = initList.toMutableList()
    val list: List<Ticket>
        get() = _list.toList()
    val price: Price
        get() = Price(list.sumOf { it.discountPrice.value })

    fun addTicket(ticket: Ticket) {
        _list.add(ticket)
    }

    fun deleteTicket(ticket: Ticket) {
        _list.remove(ticket)
    }

    fun isContainSameTicket(ticket: Ticket): Boolean = list.contains(ticket)
}
