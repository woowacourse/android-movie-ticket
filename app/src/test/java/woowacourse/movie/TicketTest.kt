package woowacourse.movie

import domain.MaxTicketsBounds
import domain.MinTicketsBounds
import domain.Ticket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `플러스 버튼을 누르면 티켓 매수가 1장 증가한다 `() {
        val ticket = Ticket()

        ticket.increaseCount()

        assertThat(ticket.count).isEqualTo(2)
    }

    @Test
    fun `마이너스 버튼을 누르면 티켓 매수가 1장 감소한다 `() {
        val ticket = Ticket()

        ticket.increaseCount()
        ticket.decreaseCount()

        assertThat(ticket.count).isEqualTo(1)
    }

    @Test
    fun `티켓이 1장일 때 마이너스 버튼을 누르면 MinTicketsBounds가 반환된다`() {
        val ticket = Ticket()

        val actual = ticket.decreaseCount()

        assertThat(actual).isInstanceOf(MinTicketsBounds::class.java)
    }

    @Test
    fun `티켓이 100장일 때 플러스 버튼을 누르면 MaxTicketsBounds가 반환된다`() {
        val ticket = Ticket()

        repeat(99) {
            ticket.increaseCount()
        }

        val actual = ticket.increaseCount()

        assertThat(actual).isInstanceOf(MaxTicketsBounds::class.java)
    }

    @Test
    fun `티켓 매수에 따라 결제 금액을 계산한다`() {
        val ticket = Ticket()

        val actual = ticket.calculatePrice(3)

        assertThat(actual).isEqualTo(39_000)
    }
}
