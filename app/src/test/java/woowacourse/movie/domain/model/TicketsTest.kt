package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketsTest {
    @Test
    fun `예매 개수가 총 가격을 계산한다`() {
        // given
        val tickets = Tickets(listOf(Ticket(Price(1_3000)), Ticket(Price(1_2000))))

        // when
        val actual = tickets.totalPrice()

        // then
        assertThat(actual).isEqualTo(2_5000)
    }
}
