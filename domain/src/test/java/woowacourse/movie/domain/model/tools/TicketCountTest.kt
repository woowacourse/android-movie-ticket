package woowacourse.movie.domain.model.tools

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TicketCountTest {
    @Test
    fun `티켓 한 장에서 추가하면 두 장이 된다`() {
        // given
        val ticketCount = TicketCount(1)
        // when
        val actual = ticketCount.plus()
        // then
        assertThat(actual).isEqualTo(TicketCount(2))
    }

    @Test
    fun `티켓 두 장에서 줄이면 한 장이 된다`() {
        // given
        val ticketCount = TicketCount(2)
        // when
        val actual = ticketCount.minus()
        // then
        assertThat(actual).isEqualTo(TicketCount(1))
    }

    @Test
    fun `티켓이 한 장일 땐 줄지 않는다`() {
        // given
        val ticketCount = TicketCount(1)
        // when
        val actual = ticketCount.minus()
        // then
        assertThat(actual).isEqualTo(TicketCount(1))
    }
}
