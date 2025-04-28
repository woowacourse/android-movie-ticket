package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movieseat.Position
import woowacourse.movie.domain.movieseat.Seat
import woowacourse.movie.domain.movieseat.Seats

class SeatsTest {
    @Test
    fun `좌석의 가격의 합을 구할 수 있다`() {
        // given
        val seats = Seats(mutableListOf(Seat(Position(0, 0)), Seat(Position(1, 1))))
        // when
        val actual = seats.reservationPrice()
        val expected = 27000
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `좌석을 추가할 수 있다`() {
        // given
        val seats = Seats(mutableListOf())
        seats.addSeat(Seat(Position(0, 0)))
        seats.addSeat(Seat(Position(1, 1)))
        // when
        val actual = seats.all
        val expected = listOf(Seat(Position(0, 0)), Seat(Position(1, 1)))

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `좌석을 빼낼 수 있다`() {
        // given
        val seats = Seats(mutableListOf(Seat(Position(0, 0)), Seat(Position(1, 1))))
        seats.removeSeat(Seat(Position(0, 0)))
        // when
        val actual = seats.all
        val expected = listOf(Seat(Position(1, 1)))

        assertThat(actual).isEqualTo(expected)
    }
}
