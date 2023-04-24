package woowacourse.movie.domain.model.tools.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.rules.SeatGradeRules.getSeatGradeByRow

class SeatsTest {

    // fake constructor
    private fun Seats(vararg locations: Location): Seats {
        val seats = locations.map { Seat(it) }
        return Seats(seats.toSet())
    }

    // fake Constructor
    private fun Seat(location: Location) = Seat(location, getSeatGradeByRow(location))

    @Test
    fun `seats 에 A1 을 추가하면 Seats 는 A1 을 포함한다`() {
        // given
        val seats = Seats()
        val seat = Seat(Location(SeatRow.A, 1))

        // when
        seats.addSeat(seat)

        // then
        assertThat(seats.value).containsExactly(seat)
    }

    @Test
    fun `A1 을 포함한 seats 에서 A1 을 제거하면 A1 을 더 이상 포함하지 않는다`() {
        // given
        val seats = Seats(
            Location(SeatRow.B, 2),
            Location(SeatRow.B, 1),
            Location(SeatRow.A, 1),
        )
        val seat = Seat(Location(SeatRow.A, 1))

        // when
        seats.removeSeat(seat)

        // then
        assertThat(seats.value).doesNotContain(seat)
    }

    @Test
    fun `seats 에 B2, B1, A1 을 추가하면 Seats 는 A1, B1, B2 순서로 가진다`() {
        // given
        val seats = Seats(
            Location(SeatRow.B, 2),
            Location(SeatRow.B, 1),
            Location(SeatRow.A, 1),
        )

        val expected = setOf(
            Seat(Location(SeatRow.A, 1)),
            Seat(Location(SeatRow.B, 1)),
            Seat(Location(SeatRow.B, 2)),
        )

        // then
        assertThat(seats.value).isEqualTo(expected)
    }
}
