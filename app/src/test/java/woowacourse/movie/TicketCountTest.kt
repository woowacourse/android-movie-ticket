package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.booking.TicketCount

class TicketCountTest {
    @Test
    fun `티켓_수량을_1씩_증가시킬_수_있다`() {
        // when+given
        val ticketCount = TicketCount()
        // then
        assertThat(ticketCount.upCount()).isEqualTo(1)
    }

    @Test
    fun `티켓_수량을_1씩_감소시킬_수_있다`() {
        // when+given
        val ticketCount = TicketCount(5)
        // then
        assertThat(ticketCount.downCount()).isEqualTo(4)
    }

    @Test
    fun `티켓_수량은_0_이하로_떨어지지_않는다`() {
        // when+given
        val ticketCount = TicketCount(0)
        // then
        assertThat(ticketCount.downCount()).isEqualTo(0)
    }
}
