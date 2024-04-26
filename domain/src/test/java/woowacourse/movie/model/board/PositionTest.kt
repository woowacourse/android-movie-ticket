package woowacourse.movie.model.board

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {
    @CsvSource(value = ["0, -1", "-1, 0", "-1, -1"])
    @ParameterizedTest
    fun `좌석 위치가 0 보다 작으면 예외 발생`(x: Int, y: Int) {
        shouldThrow<IllegalArgumentException> {
            Position(x, y)
        }
    }

    @Test
    fun `좌석 위치를 나타 낸다`() {
        shouldNotThrow<IllegalArgumentException> {
            Position(0, 0)
        }
    }
}