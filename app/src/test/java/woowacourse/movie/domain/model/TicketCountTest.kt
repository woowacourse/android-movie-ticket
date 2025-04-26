package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TicketCountTest {
    private lateinit var ticketCount: TicketCount

    @BeforeEach
    fun setup() {
        ticketCount = TicketCount()
    }

    @Test
    fun `increaseTicketCount 호출 시 티켓 수량을 증가시킨다`() {
        // given & when
        ticketCount.increase(1)
        ticketCount.increase(2)

        // then
        assertThat(ticketCount.value).isEqualTo(4)
    }

    @Test
    fun `decreaseTicketCount 호출 시 티켓 수량을 감소시킨다`() {
        // given
        ticketCount.increase(1)

        // when
        ticketCount.decrease(1)

        // then
        assertThat(ticketCount.value).isEqualTo(1)
    }

    @Test
    fun `티켓 수량이 1보다 작아질 수는 없다`() {
        // given
        ticketCount.increase(1)

        // when
        ticketCount.decrease(10)

        // then
        assertThat(ticketCount.value).isEqualTo(1)
    }
}
