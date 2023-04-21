package woowacourse.movie.domain.grade

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import woowacourse.movie.domain.grade.Position.Companion.MAXIMUM_COLUMN_INDEX
import woowacourse.movie.domain.grade.Position.Companion.MAXIMUM_ROW_INDEX
import woowacourse.movie.domain.grade.Position.Companion.START_INDEX

internal class PositionTest {

    @ParameterizedTest
    @MethodSource("ProvideWrongPosition")
    fun `Posotion은 좌석 최대 범위를 벗어날수 없다`(position: Pair<Int, Int>) {
        assertThrows<IllegalArgumentException> { Position.from(position.first, position.second) }
    }

    @Test
    fun `S등급의 좌석의 가격은 15000원이다`() {
        assertThat(
            Position.from(
                Grade.S.rowIndexRange.random(),
                (START_INDEX..MAXIMUM_COLUMN_INDEX).random()
            ).getGradePrice()
        ).isEqualTo(Grade.S.price)
    }

    @Test
    fun `A등급의 좌석의 가격은 12000원이다`() {
        assertThat(
            Position.from(
                Grade.A.rowIndexRange.random(),
                (START_INDEX..MAXIMUM_COLUMN_INDEX).random()
            ).getGradePrice()
        ).isEqualTo(Grade.A.price)
    }

    @Test
    fun `B등급의 좌석의 가격은 10000원이다`() {
        assertThat(
            Position.from(
                Grade.B.rowIndexRange.random(),
                (START_INDEX..MAXIMUM_COLUMN_INDEX).random()
            ).getGradePrice()
        ).isEqualTo(Grade.B.price)
    }

    companion object {
        @JvmStatic
        fun ProvideWrongPosition() = listOf(
            Arguments.of(
                -1 to 3
            ),
            Arguments.of(
                3 to -1
            ),
            Arguments.of(
                -3 to -3
            ),
            Arguments.of(
                MAXIMUM_ROW_INDEX + 3 to MAXIMUM_COLUMN_INDEX + 3
            )
        )
    }
}
