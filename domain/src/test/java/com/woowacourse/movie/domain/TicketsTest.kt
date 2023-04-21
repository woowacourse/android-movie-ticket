package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.policy.DiscountDecorator
import com.woowacourse.movie.domain.seat.Col
import com.woowacourse.movie.domain.seat.Row
import com.woowacourse.movie.domain.seat.SeatPosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class TicketsTest {
    @Test
    fun `티켓을 하나 추가할 수 있다`() {
        val tickets = Tickets(Ticket(1, 0))
        val anotherTicket = Ticket(1, 1)
        tickets.addTicket(anotherTicket)

        assertThat(tickets.size).isEqualTo(2)
    }

    @Test
    fun `중복된 티켓은 추가할 수 없다`() {
        val tickets = Tickets(Ticket(1, 0))
        val anotherTicket = Ticket(1, 0)
        tickets.addTicket(anotherTicket)

        assertThat(tickets.size).isEqualTo(1)
    }

    @Test
    fun `기존 티켓을 하나 제거할 수 있다`() {
        val tickets = Tickets(
            listOf(
                Ticket(1, 0),
                Ticket(1, 1)
            )
        )
        val targetTicket = Ticket(1, 1)
        tickets.removeTicket(targetTicket)

        assertThat(tickets.size).isEqualTo(1)
    }

    @Test
    fun `S좌석 1개, A좌석 1개, B좌석 1개인 티켓의 총 가격은 37_000원이다`() {
        val tickets = Tickets(
            listOf(
                Ticket(0, 0),
                Ticket(2, 0),
                Ticket(4, 0)
            )
        )
        val dateTime = LocalDateTime.of(2023, 4, 19, 14, 0)
        val discountDecorator = DiscountDecorator(dateTime)

        val actual = tickets.calculatePrice(discountDecorator)
        val expected = 37_000

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        private val DUMMY_RESERVATION = Reservation(
            Movie(0, "", LocalDate.now(), LocalDate.now(), 0, ""),
            LocalDateTime.now(),
            TicketCount()
        )

        fun SeatPosition(row: Int, col: Int): SeatPosition = SeatPosition(Row(row), Col(col))
        fun Ticket(row: Int, col: Int): Ticket = Ticket(SeatPosition(row, col))
        fun Tickets(ticket: Ticket): Tickets = Tickets(listOf(ticket), DUMMY_RESERVATION)
        fun Tickets(tickets: List<Ticket>): Tickets = Tickets(tickets, DUMMY_RESERVATION)
    }
}
