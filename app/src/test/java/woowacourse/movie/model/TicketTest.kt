package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Ticket.Companion.TICKET_PRICE

class TicketTest {
    @Test
    fun `amount의 값이 2이상일 때, sub함수 호출하면 amount의 값이 1감소한다`() {
        val ticket = Ticket(2)

        ticket.sub()

        assertThat(ticket.count).isEqualTo(1)
    }

    @Test
    fun `amount의 값이 2미만일 때, sub함수 호출하면 amount의 값은 감소하지 않는다`() {
        val ticket = Ticket(1)

        ticket.sub()

        assertThat(ticket.count).isEqualTo(1)
    }

    @Test
    fun `add함수 호출하면 amount의 값이 1증가한다`() {
        val ticket = Ticket(2)

        ticket.add()

        assertThat(ticket.count).isEqualTo(3)
    }

    @Test
    fun `amount값에 비례하여 price값을 구할 수 있다`() {
        val ticket = Ticket(3)

        assertThat(ticket.price()).isEqualTo(3 * TICKET_PRICE)
    }
}
