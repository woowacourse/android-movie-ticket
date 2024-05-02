package woowacourse.movie.model.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTest {
    @Test
    fun `좌석 1행 3열의 금액은 10,000원이다`() {
        // given
        val seat = Seat(1, 3)

        // when
        val actual = seat.amount().amount

        // then
        assertThat(actual).isEqualTo(10_000)
    }

    @Test
    fun `좌석 4행 1열의 금액은 15,000원이다`() {
        // given
        val seat = Seat(4, 1)

        // when
        val actual = seat.amount().amount

        // then
        assertThat(actual).isEqualTo(15_000)
    }

    @Test
    fun `좌석 5행 5열의 금액은 12,000원이다`() {
        // given
        val seat = Seat(5, 5)

        // when
        val actual = seat.amount().amount

        // then
        assertThat(actual).isEqualTo(12_000)
    }
}
