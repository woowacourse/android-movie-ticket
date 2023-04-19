package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatGrade
import woowacourse.movie.model.SeatRow

class SeatGradeTest {
    @Test
    fun `좌석이 A행 이면 좌석 등급이 A이다`() {
        // given
        val seat = Seat(SeatRow.A, 1)

        // when
        val actual = SeatGrade.from(seat)

        // then
        assertThat(actual).isEqualTo(SeatGrade.GRADE_A)
    }

    @Test
    fun `좌석이 C행 이면 좌석 등급이 S이다`() {
        // given
        val seat = Seat(SeatRow.C, 1)

        // when
        val actual = SeatGrade.from(seat)

        // then
        assertThat(actual).isEqualTo(SeatGrade.GRADE_S)
    }

    @Test
    fun `좌석이 E행 이면 좌석 등급이 B이다`() {
        // given
        val seat = Seat(SeatRow.E, 1)

        // when
        val actual = SeatGrade.from(seat)

        // then
        assertThat(actual).isEqualTo(SeatGrade.GRADE_B)
    }
}
