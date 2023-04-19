package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.ticket.Position
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.SeatRank
import woowacourse.movie.ticket.Ticket
import java.time.LocalDateTime

class TicketTest {
    @Test
    fun `티켓 한 장의 결제 금액을 계산한다`() {
        // given
        val ticket = Ticket(1L, LocalDateTime.of(2024, 1, 1, 9, 0), Seat(SeatRank.B, Position(1, 1)))
        val expected = 8_000

        // when
        val actual = ticket.price

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
