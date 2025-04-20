package woowacourse.movie.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TitleTest {
    @Test
    fun `영화_타이틀에는_빈_글자가_올_수_없습니다`() {
        assertThrows<IllegalArgumentException> {
            Title("")
        }
    }

    @Test
    fun `영화_타이틀에는_공백이_올_수_없습니다`() {
        assertThrows<IllegalArgumentException> {
            Title("       ")
        }
    }
}
