package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.booking.TicketCount

class TicketCountTest {
    @Test
    fun `upCount_가_실행되면_카운트가_1씩_증가하고_증가된_카운트를_리턴한다`() {
        // when+given
        val ticketCount = TicketCount()
        // then
        assertThat(ticketCount.upCount()).isEqualTo(1)
    }

    @Test
    fun `downCount_가_실행되면_카운트가_1씩_감소하고_증가된_카운트를_리턴한다`() {
        // when+given
        val ticketCount = TicketCount(5)
        // then
        assertThat(ticketCount.downCount()).isEqualTo(4)
    }

    @Test
    fun `downCount_가_실행될때_카운트는_0_미만으로_감소하지_않는다`() {
        // when+given
        val ticketCount = TicketCount(0)
        // then
        assertThat(ticketCount.downCount()).isEqualTo(0)
    }
}
