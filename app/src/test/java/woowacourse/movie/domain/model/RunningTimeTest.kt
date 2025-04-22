package woowacourse.movie.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RunningTimeTest {
    @Test
    fun `영화_상영시간이_최소_1분_미만이면_예외가_발생한다`() {
        assertThrows<IllegalArgumentException> { RunningTime(0) }
    }
}
