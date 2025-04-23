package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketsTest {
    @Test
    fun `예매 개수가 최소 예매 개수와 같은 경우 감소할 수 없다`() {
        // given
        val tickets = Tickets(listOf(Ticket(TicketType.DEFAULT)))

        // when
        val actual = tickets.canMinus()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `예매 개수가 최소 예매 개수보다 많은 경우 감소할 수 있다`() {
        // given
        val tickets = Tickets(listOf(Ticket(TicketType.DEFAULT), Ticket(TicketType.DEFAULT)))

        // when
        val actual = tickets.canMinus()

        // then
        assertThat(actual).isTrue()
    }
}
