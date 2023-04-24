package woowacourse.movie.domain.model.tools.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.rules.SeatGradeRules
import woowacourse.movie.domain.model.rules.SeatsPayment
import woowacourse.movie.domain.model.tools.Money
import java.time.LocalDateTime

class SeatsPaymentTest {

    private fun makeSeat(location: Location) = Seat(
        location,
        SeatGradeRules.getSeatGradeByRow(location),
    )

    @Test
    fun `좌석 들에 해당하는 지불 금액을 계산한다`() {
        val seats = Seats(
            setOf(
                makeSeat(Location(SeatRow.A, 1)),
                makeSeat(Location(SeatRow.C, 1)),
                makeSeat(Location(SeatRow.A, 2)),
            ),
        )
        val expected = Money(35000)
        val actual =
            SeatsPayment(seats).getDiscountedMoneyByDateTime(
                (LocalDateTime.of(2023, 5, 1, 15, 0)),
            )
        assertThat(actual).isEqualTo(expected)
    }
}
