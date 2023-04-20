package woowacourse.movie.domain.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTest {
    @Test
    fun `1번째 행의 티켓 가격은 10000원이다`() {
        val actual = Seat(1, 1).getPriceByClass()
        assertThat(actual).isEqualTo(10000)
    }

    @Test
    fun `3번째 행의 티켓 가격은 15000원이다`() {
        val actual = Seat(3, 1).getPriceByClass()
        assertThat(actual).isEqualTo(15000)
    }

    @Test
    fun `5번째 행의 티켓 가격은 12000원이다`() {
        val actual = Seat(5, 1).getPriceByClass()
        assertThat(actual).isEqualTo(12000)
    }
}
