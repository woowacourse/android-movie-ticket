package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TicketCounterTest {
    private lateinit var ticketCounter: TicketCounter

    @BeforeEach
    fun setup() {
        ticketCounter = TicketCounter()
    }

    @Test
    fun `수량의 기본 값은 1이다`() {
        assertThat(ticketCounter.ticketCount).isEqualTo(1)
    }

    @Test
    fun `플러스 버튼을 누르면 티켓개수가 감소해야한다`() {
        // when
        ticketCounter.plusTicketCount()

        // then
        assertThat(ticketCounter.ticketCount).isEqualTo(2)
    }

    @Test
    fun `마이너스 버튼을 누르면 티켓개수가 감소해야한다`() {
        // when
        ticketCounter.plusTicketCount()
        ticketCounter.plusTicketCount()
        ticketCounter.minusTicketCount()

        // then
        assertThat(ticketCounter.ticketCount).isEqualTo(2)
    }

    @Test
    fun `티켓 개수가 1일 때 마이너스 버튼을 누르면 티켓개수가 감소하지 않아야 한다`() {
        // when
        ticketCounter.minusTicketCount()

        // then
        assertThat(ticketCounter.ticketCount).isEqualTo(1)
    }
}
