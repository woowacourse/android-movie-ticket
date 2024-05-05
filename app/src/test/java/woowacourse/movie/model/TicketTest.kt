package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `티켓를 1장 증가시키면 매수가 1장 증가한다 `() {
        val ticket = Ticket(10)

        ticket.increaseCount()

        assertThat(ticket.ticketCount).isEqualTo(11)
    }

    @Test
    fun `티켓 1장 감소시키면 매수가 1장 감소한다 `() {
        val ticket = Ticket(10)

        ticket.decreaseCount()

        assertThat(ticket.ticketCount).isEqualTo(9)
    }

    @Test
    fun `티켓의 최소 수량은 1개이다`() {
        val ticket = Ticket(1)

        ticket.decreaseCount()

        assertThat(ticket.ticketCount).isEqualTo(1)
    }

    @Test
    fun `티켓의 최대 수량은 100개이다`() {
        val ticket = Ticket(100)

        ticket.increaseCount()

        assertThat(ticket.ticketCount).isEqualTo(100)
    }
}
