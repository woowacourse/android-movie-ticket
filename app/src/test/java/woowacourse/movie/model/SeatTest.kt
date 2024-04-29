package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTest {
    @Test
    fun `좌석은 행에 따라 등급이 다르다`() {
        val seat1 = Seat(row = 0, 0)
        val seat2 = Seat(row = 2, 0)
        assertThat(seat1.seatGrade).isNotEqualTo(seat2.seatGrade)
    }
}
