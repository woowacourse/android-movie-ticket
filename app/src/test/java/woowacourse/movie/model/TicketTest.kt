package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `Ticket의 count 초기 값은 1 이다`() {
        val ticket = Ticket()

        assertThat(ticket.count()).isEqualTo(1)
    }

    @Test
    fun `add함수 호출하면 count의 값이 1증가한다`() {
        val ticket = Ticket()

        ticket.addCount()

        assertThat(ticket.count()).isEqualTo(2)
    }

    @Test
    fun `count의 값이 2이상일 때, sub함수 호출하면 count의 값이 1감소한다`() {
        // given
        val ticket = Ticket()
        ticket.addCount()
        val count = ticket.count()

        // when
        ticket.subCount()

        // then
        assertThat(ticket.count()).isEqualTo(count - 1)
    }

    @Test
    fun `count의 값이 1이하일 때, sub함수 호출하면 count의 값은 감소하지 않는다`() {
        val ticket = Ticket()

        ticket.subCount()

        assertThat(ticket.count()).isEqualTo(1)
    }
}
