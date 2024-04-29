package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatsTest {
    @Test
    fun `좌석들의 총 가격을 구한다`() {
        val seats =
            Seats(
                Seat(Position(0, 0), Grade.S),
                Seat(Position(0, 1), Grade.A),
            )

        assertThat(seats.totalPrice()).isEqualTo(27_000)
    }

    @Test
    fun `count of the seats`() {
        val seats =
            Seats(
                Seat(Position(0, 0), Grade.S),
                Seat(Position(0, 1), Grade.A),
            )
        assertThat(seats.count()).isEqualTo(2)
    }

    @Test
    fun `find the max row of the seats`() {
        val seats =
            Seats(
                Seat(Position(0, 0), Grade.S),
                Seat(Position(1, 1), Grade.A),
            )
        assertThat(seats.maxRow()).isEqualTo(2)
    }

    @Test
    fun `find the max column of the seats`() {
        val seats =
            Seats(
                Seat(Position(0, 0), Grade.S),
                Seat(Position(1, 1), Grade.A),
            )
        assertThat(seats.maxColumn()).isEqualTo(2)
    }

    @Test
    fun `find seat with position`() {
        val seats =
            Seats(
                Seat(Position(0, 0), Grade.S),
                Seat(Position(1, 1), Grade.A),
            )
        val actual = seats.findSeat(Position(1, 1))

        assertThat(actual).isEqualTo(Seat(Position(1, 1), Grade.A))
    }
}
