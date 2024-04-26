package woowacourse.movie.model.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatRatingTest {
    @Test
    fun `2행 1열은 B등급이다`() {
        // given
        val seat = Seat(2, 1)

        // when
        val actual = SeatRating.from(seat)

        // then
        assertThat(actual).isEqualTo(SeatRating.B)
    }

    @Test
    fun `3행 3열은 S등급이다`() {
        // given
        val seat = Seat(3, 3)

        // when
        val actual = SeatRating.from(seat)

        // then
        assertThat(actual).isEqualTo(SeatRating.S)
    }

    @Test
    fun `5행 4열은 A등급이다`() {
        // given
        val seat = Seat(5, 4)

        // when
        val actual = SeatRating.from(seat)

        // then
        assertThat(actual).isEqualTo(SeatRating.A)
    }

    @Test
    fun `B등급 좌석의 금액은 10,000원이다`() {
        val actual = SeatRating.B.amount.amount
        assertThat(actual).isEqualTo(10_000)
    }

    @Test
    fun `S등급 좌석의 금액은 15,000원이다`() {
        val actual = SeatRating.S.amount.amount
        assertThat(actual).isEqualTo(15_000)
    }

    @Test
    fun `A등급 좌석의 금액은 12,000원이다`() {
        val actual = SeatRating.A.amount.amount
        assertThat(actual).isEqualTo(12_000)
    }
}
