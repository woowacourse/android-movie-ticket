package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketsTest {
    @Test
    fun `주어진_티켓_수에_맞는_총_금액을_계산하여_반환한다`() {
        val tickets = Tickets(Count(3), 13000)
        assertThat(tickets.totalPrice).isEqualTo(39000)
    }
}
