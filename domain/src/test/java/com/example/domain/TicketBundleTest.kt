package com.example.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TicketBundleTest {
    @Test
    fun `티켓 가격의 총 합을 구한다`() {
        val ticketBundle = TicketBundle(
            listOf(
                Ticket(Seat(0)),
                Ticket(Seat(1)),
                Ticket(Seat(2))
            )
        )
        val actual = ticketBundle.calculateTotalPrice(
            "2023-04-10",
            "13:00"
        )
        assertEquals(27000, actual)
    }

    @Test
    fun `티켓을 추가한다`() {
        val ticketBundle = TicketBundle(
            listOf(Ticket(Seat(0)))
        )
        ticketBundle.putTicket(Seat(1))

        assertEquals(
            listOf("A1", "A2"),
            ticketBundle.getSeatNames()
        )
    }

    @Test
    fun `티켓을 뺀다`() {
        val ticketBundle = TicketBundle(
            listOf(
                Ticket(Seat(0)),
                Ticket(Seat(1))
            )
        )
        ticketBundle.popTicket(Seat(1))

        assertEquals(
            listOf("A1"),
            ticketBundle.getSeatNames()
        )
    }
}
