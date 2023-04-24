package woowacourse.movie.domain.model.tools.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TheaterTest {
    @Test
    fun `Theater 에서 좌석 A1 의 등급은 B 이다`() {
        // given
        val theater = Theater.of(SeatRow.values().toList(), listOf(0, 1, 2, 3))
        val expected = SeatGrade.GRADE_B

        // when
        val actual = theater.findSeat(Location(SeatRow.A, 1))

        // then
        assertThat(actual?.grade).isEqualTo(expected)
    }
}
