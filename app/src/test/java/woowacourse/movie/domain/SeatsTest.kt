package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.A_CLASS
import woowacourse.movie.B_CLASS
import woowacourse.movie.S_CLASS

class SeatsTest {
    private val seats =
        Seats(
            setOf(
                B_CLASS,
                A_CLASS,
                S_CLASS,
            ),
        )

    @Test
    fun `해당 좌석을 포함하고 있으면 true를 반환한다`() {
        // given & when
        val actual = seats.has(B_CLASS)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `좌석의 총 가격을 계산한다`() {
        // given & when
        val actual = seats.totalPrice()
        val expected = 37_000

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
