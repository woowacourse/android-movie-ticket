package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Location
import woowacourse.movie.model.Money
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatGrade
import woowacourse.movie.model.SeatRow
import woowacourse.movie.model.Ticket
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
            listOf(
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
