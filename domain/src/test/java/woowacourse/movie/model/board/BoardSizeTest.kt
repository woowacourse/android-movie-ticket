package woowacourse.movie.model.board

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BoardSizeTest {
    @ParameterizedTest
    @CsvSource(value = ["2, 2", "2, 3", "3, 2"])
    fun `너비과 높이가 3 미만 이면 예외 발생`(
        width: Int,
        height: Int,
    ) {
        shouldThrow<IllegalArgumentException> {
            BoardSize(width, height)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["3, 3", "4, 4", "10, 10"])
    fun `너비과 높이는 최소 3 이상`(
        width: Int,
        height: Int,
    ) {
        shouldNotThrow<IllegalArgumentException> {
            BoardSize(width, height)
        }
    }

    @Test
    fun `위치가 (3,2)이고 보드판 크기 (3,4) 일 때, Board 안에 있다`() {
        val boardSize = BoardSize(3, 4)
        val position = Position(3, 2)
        boardSize.isInBounds(position).shouldBeTrue()
    }

    @Test
    fun `위치가 (3,3)이고 보드판 크기 (3,3) 일 때, Board 안에 없다`() {
        val boardSize = BoardSize(3, 3)
        val position = Position(3, 3)
        boardSize.isInBounds(position).shouldBeFalse()
    }
}
