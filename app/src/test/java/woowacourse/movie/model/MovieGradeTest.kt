package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.MovieGrade.Companion.judgeGradeByRow

class MovieGradeTest {
    @Test
    fun `0행~1행사이의 좌석 등급은 B이다`() {
        assertThat(judgeGradeByRow(0)).isEqualTo(MovieGrade.B_GRADE)
        assertThat(judgeGradeByRow(1)).isEqualTo(MovieGrade.B_GRADE)
    }

    @Test
    fun `2행~3행사이의 좌석 등급은 S이다`() {
        assertThat(judgeGradeByRow(2)).isEqualTo(MovieGrade.S_GRADE)
        assertThat(judgeGradeByRow(3)).isEqualTo(MovieGrade.S_GRADE)
    }

    @Test
    fun `4행의 좌석 등급은 B이다`() {
        assertThat(judgeGradeByRow(4)).isEqualTo(MovieGrade.A_GRADE)
    }
}
