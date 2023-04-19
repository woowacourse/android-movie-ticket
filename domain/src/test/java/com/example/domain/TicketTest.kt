package com.example.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `B등급 티켓에 대해 다른 할인 없이 무비데이라면 10퍼센트 할인한다`() {
        val actual = Ticket(Seat(0)).getTicketPrice("2023-04-10", "14:00")
        assertEquals(actual, 9000)
    }

    @Test
    fun `B등급 티켓에 대해 다른 할인 없이 조조 시간이라면 2000원 할인한다`() {
        val actual = Ticket(Seat(0)).getTicketPrice("2023-04-09", "10:00")
        assertEquals(actual, 8000)
    }

    @Test
    fun `B등급 티켓에 대해 다른 할인 없이 심야 시간이라면 2000원 할인한다`() {
        val actual = Ticket(Seat(0)).getTicketPrice("2023-04-09", "20:00")
        assertEquals(actual, 8000)
    }

    @Test
    fun `B등급 티켓에 대해 할인이 없을시 티켓의 정가를 반환한다`() {
        val actual = Ticket(Seat(0)).getTicketPrice("2023-04-09", "19:00")
        assertEquals(actual, 10000)
    }

    @Test
    fun `B등급 티켓에 대해 무비데이 할인과 시간대 할인이 중복된다면 무비데이 할인이 먼저 계산된다`() {
        val actual = Ticket(Seat(0)).getTicketPrice("2023-04-10", "10:00")
        assertEquals(actual, 7000)
    }

    @Test
    fun `S등급 티켓에 대해 다른 할인 없이 무비데이라면 10퍼센트 할인한다`() {
        val actual = Ticket(Seat(9)).getTicketPrice("2023-04-10", "14:00")
        assertEquals(actual, 13500)
    }

    @Test
    fun `S등급 티켓에 대해 다른 할인 없이 조조 시간이라면 2000원 할인한다`() {
        val actual = Ticket(Seat(9)).getTicketPrice("2023-04-09", "10:00")
        assertEquals(actual, 13000)
    }

    @Test
    fun `S등급 티켓에 대해 다른 할인 없이 심야 시간이라면 2000원 할인한다`() {
        val actual = Ticket(Seat(9)).getTicketPrice("2023-04-09", "20:00")
        assertEquals(actual, 13000)
    }

    @Test
    fun `S등급 티켓에 대해 할인이 없을시 티켓의 정가를 반환한다`() {
        val actual = Ticket(Seat(9)).getTicketPrice("2023-04-09", "19:00")
        assertEquals(actual, 15000)
    }

    @Test
    fun `S등급 티켓에 대해 무비데이 할인과 시간대 할인이 중복된다면 무비데이 할인이 먼저 계산된다`() {
        val actual = Ticket(Seat(9)).getTicketPrice("2023-04-10", "10:00")
        assertEquals(actual, 11500)
    }

    @Test
    fun `A등급 티켓에 대해 다른 할인 없이 무비데이라면 10퍼센트 할인한다`() {
        val actual = Ticket(Seat(17)).getTicketPrice("2023-04-10", "14:00")
        assertEquals(actual, 10800)
    }

    @Test
    fun `A등급 티켓에 대해 다른 할인 없이 조조 시간이라면 2000원 할인한다`() {
        val actual = Ticket(Seat(17)).getTicketPrice("2023-04-09", "10:00")
        assertEquals(actual, 10000)
    }

    @Test
    fun `A등급 티켓에 대해 다른 할인 없이 심야 시간이라면 2000원 할인한다`() {
        val actual = Ticket(Seat(17)).getTicketPrice("2023-04-09", "20:00")
        assertEquals(actual, 10000)
    }

    @Test
    fun `A등급 티켓에 대해 할인이 없을시 티켓의 정가를 반환한다`() {
        val actual = Ticket(Seat(17)).getTicketPrice("2023-04-09", "19:00")
        assertEquals(actual, 12000)
    }

    @Test
    fun `A등급 티켓에 대해 무비데이 할인과 시간대 할인이 중복된다면 무비데이 할인이 먼저 계산된다`() {
        val actual = Ticket(Seat(17)).getTicketPrice("2023-04-10", "10:00")
        assertEquals(actual, 8800)
    }
}
