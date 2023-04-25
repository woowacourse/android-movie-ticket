package com.example.domain

class TicketBundle(tickets: List<Ticket> = emptyList()) {
    val tickets: List<Ticket>
        get() = _tickets.toList()

    private val _tickets: MutableList<Ticket> = tickets.toMutableList()
    fun calculateTotalPrice(date: String, time: String): Int = _tickets.sumOf {
        it.getTicketPrice(date, time)
    }

    fun getSeatNames(): List<String> {
        return tickets.map { it.getSeatName() }
    }

    fun putTicket(seat: Seat) {
        _tickets.add(Ticket(seat))
    }

    fun popTicket(seat: Seat) {
        _tickets.remove(
            _tickets.find {
                it.getSeatName() == seat.getSeatName()
            }
        )
    }
}
