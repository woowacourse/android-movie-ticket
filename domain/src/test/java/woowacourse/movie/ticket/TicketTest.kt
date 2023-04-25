package woowacourse.movie.ticket

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TicketTest {
    @Test
    fun `티켓 한 장의 결제 금액을 계산한다`() {
        // given
        val screeningDateTime = LocalDateTime.of(2024, 1, 1, 9, 0)
        val seat = Seat(SeatRank.B, Position(1, 1))
        val ticket = Ticket(1L, screeningDateTime, seat)
        val expected = 8_000

        // when
        val actual = ticket.price

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
