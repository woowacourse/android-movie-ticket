package woowacourse.movie.domain.tools.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.tools.Money
import java.time.LocalDateTime

class SeatsTest {

    private fun makeSeat(location: Location) = Seat(location, SeatGrade.from(location))

    @Test
    fun `seats 에 A1 을 추가하면 Seats 는 A1 을 포함한다`() {
        // given
        val seats = Seats()
        val seat = makeSeat(Location(SeatRow.A, 1))

        // when
        seats.addSeat(seat)

        // then
        assertThat(seats.value).containsExactly(seat)
    }

    @Test
    fun `A1 을 포함한 seats 에서 A1 을 제거하면 A1 을 더 이상 포함하지 않는다`() {
        // given
        val seats = Seats(
            listOf(
                makeSeat(Location(SeatRow.B, 2)),
                makeSeat(Location(SeatRow.B, 1)),
                makeSeat(Location(SeatRow.A, 1)),
            ),
        )
        val seat = makeSeat(Location(SeatRow.A, 1))

        // when
        seats.removeSeat(seat)

        // then
        assertThat(seats.value).doesNotContain(seat)
    }

    @Test
    fun `seats 에 B2, B1, A1 을 추가하고 정렬하면 Seats 는 A1, B1, B2 를 가진다`() {
        // given
        val seats = Seats(
            listOf(
                makeSeat(Location(SeatRow.B, 2)),
                makeSeat(Location(SeatRow.B, 1)),
                makeSeat(Location(SeatRow.A, 1)),
            ),
        )
        val expected = listOf(
            makeSeat(Location(SeatRow.A, 1)),
            makeSeat(Location(SeatRow.B, 1)),
            makeSeat(Location(SeatRow.B, 2)),
        )

        // when
        val actual = seats.sorted()

        // then
        assertThat(actual.value).isEqualTo(expected)
    }

    @Test
    fun `좌석 들에 해당하는 지불 금액을 계산한다`() {
        val seats = Seats(
            listOf(
                makeSeat(Location(SeatRow.A, 1)),
                makeSeat(Location(SeatRow.C, 1)),
                makeSeat(Location(SeatRow.A, 2)),
            ),
        )
        val expected = Money(35000)
        val actual = seats.getPaymentMoney(LocalDateTime.of(2023, 5, 1, 15, 0))

        assertThat(actual).isEqualTo(expected)
    }
}
