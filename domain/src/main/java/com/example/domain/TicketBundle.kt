package com.example.domain

class TicketBundle(private val tickets: List<Ticket>) {

    constructor(count: Int) : this(
        mutableListOf<Ticket>().apply {
            repeat(count) {
                add(Ticket(Seat(0)))
            }
        }.toList()
    )

    fun calculateTotalPrice(date: String, time: String): Int = tickets.sumOf {
        it.getTicketPrice(date, time)
    }

    fun getSeatNames(): List<String> {
        return tickets.map { it.getSeatName() }
    }
}
