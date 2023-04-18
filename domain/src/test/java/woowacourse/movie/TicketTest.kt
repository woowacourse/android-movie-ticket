package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TicketTest {
    @Test
    fun `전체 결제 금액을 계산한다`() {
        // given
        val ticket = Ticket(1L, LocalDateTime.of(2024, 1, 1, 9, 0), 3)
        val expected = 33000

        // when
        val actual = ticket.getPaymentAmount()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
