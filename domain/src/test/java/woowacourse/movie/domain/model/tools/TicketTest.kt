package woowacourse.movie.domain.model.tools

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.tools.seat.Location
import woowacourse.movie.domain.model.tools.seat.Seat
import woowacourse.movie.domain.model.tools.seat.SeatGrade
import woowacourse.movie.domain.model.tools.seat.SeatRow
import java.time.LocalDateTime

class TicketTest {

    private fun makeSeat(location: Location) = Seat(location, SeatGrade.from(location))

    @Test
    fun `전체 결제 금액을 계산한다`() {
        // given
        val ticket = Ticket(
            1L,
            LocalDateTime.of(2024, 1, 1, 9, 0),
            3,
            sortedSetOf(
                makeSeat(Location(SeatRow.A, 1)),
                makeSeat(Location(SeatRow.C, 1)),
                makeSeat(Location(SeatRow.E, 1)),
            ),
        )
        val expected = Money(31000)

        // when
        val actual = ticket.getPaymentMoney()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
