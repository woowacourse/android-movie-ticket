package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketsTest {
    @Test
    fun `예매 개수가 총 가격을 계산한다`() {
        // given
        val tickets = Tickets(listOf(Ticket(Price(13000)), Ticket(Price(12000))))

        // when
        val actual = tickets.totalPrice()

        // then
        assertThat(actual).isEqualTo(25000)
    }
}
