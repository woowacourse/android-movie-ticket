package woowacourse.movie.model.board

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DefaultSeatGradePolicyTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1])
    fun `0~1행 좌석은 B 등급`(y: Int) {
        // given
        val position = Position(0, y)
        val expect = SeatGrade.B
        // when
        val actual = DefaultSeatGradePolicy.grade(position)
        // then
        actual shouldBe expect
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3])
    fun `2~3행 좌석은 S 등급`(y: Int) {
        // given
        val position = Position(0, y)
        val expect = SeatGrade.S
        // when
        val actual = DefaultSeatGradePolicy.grade(position)
        // then
        actual shouldBe expect
    }

    @ParameterizedTest
    @ValueSource(ints = [4, 5, 10])
    fun `0~3행을 제외한 좌석은 A 등급`(y: Int) {
        // given
        val position = Position(0, y)
        val expect = SeatGrade.A
        // when
        val actual = DefaultSeatGradePolicy.grade(position)
        // then
        actual shouldBe expect
    }
}