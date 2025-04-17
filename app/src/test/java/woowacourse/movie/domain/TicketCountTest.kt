package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketCountTest {
    @Test
    fun `예매 개수가 최소 예매 개수와 같은 경우 감소할 수 없다`() {
        // given
        val ticketCount = TicketCount(1)

        // when
        val actual = ticketCount.canMinus()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `예매 개수가 최소 예매 개수보다 많은 경우 감소할 수 있다`() {
        // given
        val ticketCount = TicketCount(2)

        // when
        val actual = ticketCount.canMinus()

        // then
        assertThat(actual).isTrue()
    }
}
