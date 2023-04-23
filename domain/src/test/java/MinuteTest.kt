package woowacourse.movie.domain

import org.junit.Assert.assertThrows
import org.junit.Test

class MinuteTest {

    @Test
    fun `분을 음수로 생성하면 에러가 발생한다`() {
        assertThrows("[ERROR] 분은 음수일 수 없습니다.", IllegalArgumentException::class.java) {
            Minute(-1)
        }
    }
}
