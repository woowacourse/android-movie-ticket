package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movieseat.Position
import woowacourse.movie.domain.movieseat.Seat

class SeatTest {
    @Test
    fun `좌석이 위치의 row가 4이면 좌석의 가격은 12000원이다`() {
        // given
        val seat = Seat(Position(4, 0))
        // when
        val actual = seat.seatPrice()
        val expected = 12000

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `좌석의 위치의 row가 2,3 좌석의 가격은 15000원이다`() {
        // given
        val seat1 = Seat(Position(2, 0))
        val seat2 = Seat(Position(3, 0))
        // when
        val actual1 = seat1.seatPrice()
        val expected1 = 15000
        val actual2 = seat2.seatPrice()
        val expected2 = 15000
        // then
        assertThat(actual1).isEqualTo(expected1)
        assertThat(actual2).isEqualTo(expected2)
    }

    @Test
    fun `좌석이 위치의 row가 0,1 면 좌석의 가격은 10000원이다`() {
        // given
        val seat1 = Seat(Position(0, 0))
        val seat2 = Seat(Position(1, 0))
        // when
        val actual1 = seat1.seatPrice()
        val expected1 = 10000
        val actual2 = seat2.seatPrice()
        val expected2 = 10000
        // then
        assertThat(actual1).isEqualTo(expected1)
        assertThat(actual2).isEqualTo(expected2)
    }
}
