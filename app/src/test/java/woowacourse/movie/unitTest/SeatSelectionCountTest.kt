package woowacourse.movie.unitTest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.TicketCount

class SeatSelectionCountTest {
    @Test
    fun `티켓_수량을_1씩_증가시킬_수_있다`() {
        // given
        val ticketCount = TicketCount()
        // when
        ticketCount.upCount()
        // then
        assertThat(ticketCount.count).isEqualTo(1)
    }

    @Test
    fun `티켓_수량을_1씩_감소시킬_수_있다`() {
        // given
        val ticketCount = TicketCount()
        // when
        ticketCount.upCount()
        ticketCount.downCount()
        // then
        assertThat(ticketCount.count).isEqualTo(0)
    }

    @Test
    fun `티켓_수량은_0_이하로_떨어지지_않는다`() {
        // given
        val ticketCount = TicketCount()
        // when
        ticketCount.downCount()
        // then
        assertThat(ticketCount.count).isEqualTo(0)
    }
}
