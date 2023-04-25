package woowacourse.movie.policy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.ticket.Position
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.SeatRank
import woowacourse.movie.ticket.Ticket
import java.time.LocalDateTime

class SeatPolicyTest {
    @Test
    fun `B등급 좌석의 가격은 10_000원이다`() {
        // given
        val ticket = Ticket(0, LocalDateTime.of(2024, 3, 1, 16, 0), Seat(SeatRank.B, Position(1, 1)))
        val expected = 10_000

        // when
        val actual: Int = TicketPriceAdapter().getPayment(ticket)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `A등급 좌석의 가격은 12_000원이다`() {
        // given
        val ticket = Ticket(0, LocalDateTime.of(2024, 3, 1, 16, 0), Seat(SeatRank.A, Position(4, 3)))
        val expected = 12_000

        // when
        val actual: Int = TicketPriceAdapter().getPayment(ticket)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `S등급 좌석의 가격은 15_000원이다`() {
        // given
        val ticket = Ticket(0, LocalDateTime.of(2024, 3, 1, 16, 0), Seat(SeatRank.S, Position(3, 2)))
        val expected = 15_000

        // when
        val actual: Int = TicketPriceAdapter().getPayment(ticket)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
