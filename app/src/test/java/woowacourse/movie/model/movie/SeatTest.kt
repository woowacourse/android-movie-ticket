package woowacourse.movie.model.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTest {
    @Test
    fun `A열은 B클래스의 가격을 가진다`() {
        // given
        val seat = Seat(SeatRow.A, 0)

        // when
        val actual = seat.price()

        // then
        assertThat(actual).isEqualTo(SeatClass.B.amount)
    }

    @Test
    fun `C열은 S클래스의 가격을 가진다`() {
        // given
        val seat = Seat(SeatRow.C, 0)

        // when
        val actual = seat.price()

        // then
        assertThat(actual).isEqualTo(SeatClass.S.amount)
    }
}
