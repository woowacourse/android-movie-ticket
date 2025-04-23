package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketCountTest {
    @Test
    fun `티켓 수량은 최소 티켓 수량보다 작은 값을 가질 수 없다`() {
        assertThat(TicketCount.create(value = 0) is TicketCountResult.Error).isTrue()
    }

    @Test
    fun `티켓수량을 1 감소시킨 티켓수량을 얻을 수 있다`() {
        val ticketCount = TicketCount.create(value = 10).getOrThrow()
        assertThat(ticketCount.value).isEqualTo(10)
        assertThat(ticketCount.decrease().getOrThrow().value).isEqualTo(9)
    }

    @Test
    fun `티켓수량을 1 증가시킨 티켓수량을 얻을 수 있다`() {
        val ticketCount = TicketCount.create(value = 10).getOrThrow()
        assertThat(ticketCount.value).isEqualTo(10)
        assertThat(ticketCount.increase().getOrThrow().value).isEqualTo(11)
    }

    @Test
    fun `최소 티켓 수량으로 객체를 생성할 수 있다`() {
        val ticketCount = TicketCount.createDefault()
        assertThat(ticketCount.value).isEqualTo(1)
    }

    @Test
    fun `티켓수량은 최소 티켓 수량 밑으로 감소할 수 없다`() {
        val ticketCount = TicketCount.createDefault()
        assertThat(ticketCount.decrease() is TicketCountResult.Error).isTrue()
    }
}
