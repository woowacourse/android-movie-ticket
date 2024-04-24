package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTest {
    @Test
    fun `영화 티켓 한 장은 13,000원이다`() {
        // given
        val ticket = Ticket(ReservationCount(1))

        // when
        val actual = ticket.amount()

        // then
        assertThat(actual).isEqualTo(13000)
    }

    @Test
    fun `영화 티켓 세 장의 가격은 39,000원이다`() {
        // given
        val ticket = Ticket(ReservationCount(3))

        // when
        val actual = ticket.amount()

        // then
        assertThat(actual).isEqualTo(39000)
    }
}
