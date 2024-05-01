package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatGrade

class SeatTest {
    @Test
    fun `SeatGrade에 따라 Seat에 맞는 금액을 가지고 있다`() {
        assertThat(SEAT_GRADE_S.price).isEqualTo(PRICE_GRADE_S)
        assertThat(SEAT_GRADE_A.price).isEqualTo(PRICE_GRADE_A)
        assertThat(SEAT_GRADE_B.price).isEqualTo(PRICE_GRADE_B)
    }

    @Test
    fun `주어진 row 와 col 값으로 Seat 와 동일한 위치인지 확인할 수 있다`() {
        assertThat(SEAT_GRADE_A.samePositionWith("A", 1)).isTrue
    }

    companion object {
        private val SEAT_GRADE_S = Seat("A", 1, SeatGrade.S)
        private val SEAT_GRADE_A = Seat("A", 1, SeatGrade.A)
        private val SEAT_GRADE_B = Seat("A", 1, SeatGrade.B)

        private const val PRICE_GRADE_S = 15000
        private const val PRICE_GRADE_A = 12000
        private const val PRICE_GRADE_B = 10000
    }
}
