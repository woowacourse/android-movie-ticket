package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `티켓를 1장 증가시키면 매수가 1장 증가한다 `() {
        val ticket = Ticket()

        ticket.increaseCount()

        assertThat(ticket.count).isEqualTo(2)
    }

    @Test
    fun `티켓 1장 감소시키면 매수가 1장 감소한다 `() {
        val ticket = Ticket()

        ticket.increaseCount()
        ticket.decreaseCount()

        assertThat(ticket.count).isEqualTo(1)
    }

    @Test
    fun `티켓의 최소 수량은 1개이다`() {
        val ticket = Ticket()

        ticket.decreaseCount()

        assertThat(ticket.count).isEqualTo(1)
    }

    @Test
    fun `티켓의 최대 수량은 100개이다`() {
        val ticket = Ticket()

        repeat(99) {
            ticket.increaseCount()
        }

        ticket.increaseCount()

        assertThat(ticket.count).isEqualTo(99)
    }

    @Test
    fun `티켓 매수에 따라 결제 금액을 계산한다`() {
        val ticket = Ticket()

        ticket.increaseCount()
        ticket.increaseCount()

        val actual = ticket.calculatePrice()

        assertThat(actual).isEqualTo(39_000)
    }
}
