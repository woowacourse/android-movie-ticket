package woowacourse.movie.domain.model.tools

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.rules.SeatGradeRules
import woowacourse.movie.domain.model.tools.seat.Location
import woowacourse.movie.domain.model.tools.seat.Seat
import woowacourse.movie.domain.model.tools.seat.SeatRow
import woowacourse.movie.domain.model.tools.seat.Seats
import java.time.LocalDateTime

class TicketTest {

    // fake constructor
    private fun Seats(vararg locations: Location): Seats {
        val seats = locations.map {
            Seat(it, SeatGradeRules.getSeatGradeByRow(it))
        }
        return Seats(seats.toSet())
    }

    @Test
    fun `전체 결제 금액을 계산한다`() {
        // given
        val ticket = Ticket(
            1L,
            LocalDateTime.of(2024, 1, 1, 9, 0),
            3,
            Seats(
                Location(SeatRow.A, 1),
                Location(SeatRow.C, 1),
                Location(SeatRow.E, 1),
            ),
        )
        val expected = Money(31000)

        // when
        val actual = ticket.getPaymentMoney()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
