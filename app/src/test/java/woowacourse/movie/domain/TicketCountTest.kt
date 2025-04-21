package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TicketCountTest {
    @Test
    fun `티켓 수량은 최소 티켓 수량보다 작은 값을 가질 수 없다`() {
        assertThrows<IllegalArgumentException> { TicketCount.create(value = 0) }
    }

    @Test
    fun `티켓수량을 1 감소시킨 티켓수량을 얻을 수 있다`() {
        val ticketCount = TicketCount.create(value = 10)
        assertThat(ticketCount.value).isEqualTo(10)
        assertThat(ticketCount.decrease().value).isEqualTo(9)
    }

    @Test
    fun `티켓수량을 1 증가시킨 티켓수량을 얻을 수 있다`() {
        val ticketCount = TicketCount.create(value = 10)
        assertThat(ticketCount.value).isEqualTo(10)
        assertThat(ticketCount.increase().value).isEqualTo(11)
    }

    @Test
    fun `티켓수량은 최소 티켓 수량 밑으로 감소할 수 없다`() {
        val ticketCount = TicketCount.create(value = 1)
        assertThat(ticketCount.value).isEqualTo(1)
        assertThat(ticketCount.decrease().value).isEqualTo(1)
    }
}
