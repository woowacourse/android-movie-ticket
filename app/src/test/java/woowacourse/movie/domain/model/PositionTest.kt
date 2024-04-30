package woowacourse.movie.domain.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {
    @ParameterizedTest
    @CsvSource(value = ["0, 0", "1, 1", "4, 2", "4, 3"])
    fun `위치는 0,0 ~ 4,3 사이 이면 정상적인 인스턴스이다`(
        row: Int,
        col: Int,
    ) {
        assertDoesNotThrow {
            Position(row, col)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["-2, -2", "-1, -1", "4, 4", "5, 5"])
    fun `위치는 0,0 ~ 5,5 사이가 아니면 예외를 던진다`(
        row: Int,
        col: Int,
    ) {
        assertThrows<IllegalArgumentException> { Position(row, col) }
    }
}
