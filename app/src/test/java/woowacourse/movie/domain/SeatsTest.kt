package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatsTest {
    @Test
    fun `좌석의 가격의 합을 구할 수 있다`() {
        // given
        val seats = Seats(mutableListOf(Seat(Position(0, 0)), Seat(Position(1, 1))))
        // when
        val actual = seats.reservationPrice()
        val expected = 25000

        assertThat(actual).isEqualTo(expected)
    }
}
