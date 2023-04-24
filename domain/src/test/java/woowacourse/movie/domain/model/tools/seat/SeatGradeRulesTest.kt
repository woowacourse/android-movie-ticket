package woowacourse.movie.domain.model.tools.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.rules.SeatGradeRules

class SeatGradeRulesTest {
    @Test
    fun `위치가 A행 이면 좌석 등급이 A이다`() {
        // given
        val location = Location(SeatRow.A, 1)

        // when
        val actual = SeatGradeRules.getSeatGradeByRow(location)

        // then
        assertThat(actual).isEqualTo(SeatGrade.GRADE_B)
    }

    @Test
    fun `위치가 C행 이면 좌석 등급이 S이다`() {
        // given
        val location = Location(SeatRow.C, 1)

        // when
        val actual = SeatGradeRules.getSeatGradeByRow(location)

        // then
        assertThat(actual).isEqualTo(SeatGrade.GRADE_S)
    }

    @Test
    fun `위치가 E행 이면 좌석 등급이 B이다`() {
        // given
        val location = Location(SeatRow.E, 1)

        // when
        val actual = SeatGradeRules.getSeatGradeByRow(location)

        // then
        assertThat(actual).isEqualTo(SeatGrade.GRADE_A)
    }
}
