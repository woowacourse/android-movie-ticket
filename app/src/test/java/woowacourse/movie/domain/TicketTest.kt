package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.fixture.A1
import woowacourse.movie.fixture.A2
import woowacourse.movie.fixture.A3
import woowacourse.movie.fixture.A4
import woowacourse.movie.fixture.B3
import woowacourse.movie.fixture.D2
import woowacourse.movie.fixture.E4
import woowacourse.movie.fixture.HARRY_POTTER_01

class TicketTest {
    @Test
    fun `특정 좌석이 포함되어 있는지 확인한다`() {
        // given
        val seats = Seats(setOf(A1, A2, A3))
        val ticket =
            Ticket(
                movie = HARRY_POTTER_01,
                seats = seats,
            )

        assertThat(ticket.contains(A1)).isTrue()
        assertThat(ticket.contains(A4)).isFalse()
    }

    @Test
    fun `선택된 좌석이 없으면 총 금액은 0원이다`() {
        // given
        val seats = Seats(setOf())
        val ticket =
            Ticket(
                movie = HARRY_POTTER_01,
                seats = seats,
            )

        // when
        val expected = 0

        // then
        assertThat(ticket.totalPrice()).isEqualTo(expected)
    }

    @Test
    fun `B3,D2 좌석이 있으면 총 금액은 25,000원이다`() {
        // given
        val seats = Seats(setOf(B3, D2))
        val ticket =
            Ticket(
                movie = HARRY_POTTER_01,
                seats = seats,
            )

        // when
        val expected = 25_000

        // then
        assertThat(ticket.totalPrice()).isEqualTo(expected)
    }

    @Test
    fun `A1,B3,D2,E4 좌석이 있으면 총 금액은 47,000원이다`() {
        // given
        val seats = Seats(setOf(A1, B3, D2, E4))
        val ticket =
            Ticket(
                movie = HARRY_POTTER_01,
                seats = seats,
            )

        // when
        val expected = 47_000

        // then
        assertThat(ticket.totalPrice()).isEqualTo(expected)
    }

    @Test
    fun `선택된 인원 수만큼 좌석이 선택되었는지 확인한다`() {
        // given
        val ticketCount = 4
        val seats = Seats(setOf(A1, B3, D2, E4))
        val ticket =
            Ticket(
                movie = HARRY_POTTER_01,
                count = ticketCount,
                seats = seats,
            )

        // when
        val expected = true

        // then
        assertThat(ticket.isSeatsAllSelected()).isEqualTo(expected)
    }
}
