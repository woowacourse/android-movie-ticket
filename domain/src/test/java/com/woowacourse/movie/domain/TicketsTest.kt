package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.policy.DiscountDecorator
import com.woowacourse.movie.domain.seat.Col
import com.woowacourse.movie.domain.seat.Row
import com.woowacourse.movie.domain.seat.SeatPosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TicketsTest {
    @Test
    fun `티켓을 하나 추가할 수 있다`() {
        val tickets = Tickets(Ticket(1, 0))
        val anotherTicket = Ticket(1, 1)
        tickets.addOrRemoveTicket(anotherTicket)

        assertThat(tickets.size).isEqualTo(2)
    }

    @Test
    fun `티켓이 중복되면 해당 티켓을 제거한다`() {
        val tickets = Tickets(Ticket(1, 0))
        val anotherTicket = Ticket(1, 0)

        tickets.addOrRemoveTicket(anotherTicket)

        assertThat(tickets.size).isEqualTo(0)
    }

    @Test
    fun `포지션이 (0,0), (1,1)인 티켓들이 있을 때 (0,0) 티켓을 찾을 수 있다`() {
        val targetTicket = Ticket(0, 0)
        val tickets = Tickets(
            setOf(
                Ticket(1, 1),
                targetTicket
            )
        )

        assertThat(tickets.find(targetTicket)).isNotNull
    }

    @Test
    fun `아무 티켓도 존재하지 않으면 티켓은 빈 상태다`() {
        val tickets = Tickets(setOf())

        assertThat(tickets.isEmpty()).isTrue
    }

    @Test
    fun `(1,3), (1,0), (0,0) 좌석을 가진 티켓이 있을 때 행, 열로 각각 정렬된 티켓을 가질 수 있다`() {
        val actual = Tickets(
            setOf(
                Ticket(1, 3),
                Ticket(1, 0),
                Ticket(0, 0)
            )
        )

        val expected = Tickets(
            setOf(
                Ticket(0, 0),
                Ticket(1, 0),
                Ticket(1, 3)
            )
        )

        assertThat(actual.tickets).isEqualTo(expected.tickets)
    }

    @Test
    fun `S좌석 1개, A좌석 1개, B좌석 1개인 티켓의 총 가격은 37_000원이다`() {
        val tickets = Tickets(
            setOf(
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
        fun SeatPosition(row: Int, col: Int): SeatPosition = SeatPosition(Row(row), Col(col))
        fun Ticket(row: Int, col: Int): Ticket = Ticket(SeatPosition(row, col))
        fun Tickets(ticket: Ticket): Tickets = Tickets(setOf(ticket))
    }
}
