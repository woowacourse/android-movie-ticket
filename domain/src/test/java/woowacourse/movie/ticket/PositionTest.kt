package woowacourse.movie.ticket

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PositionTest {
    @Test
    fun `0 미만의 값은 row값이 될 수 없다`() {
        // given
        // when
        // then
        assertThrows<IllegalArgumentException> { Position(-1, 1) }
            .shouldHaveMessage("좌석의 행은 음수일 수 없습니다. 현재 행: -1")
    }

    @Test
    fun `0 미만의 값은 column값이 될 수 없다`() {
        assertThrows<IllegalArgumentException> { Position(1, -1) }
            .shouldHaveMessage("좌석의 열은 음수일 수 없습니다. 현재 열: -1")
    }
}
