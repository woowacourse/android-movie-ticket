package woowacourse.movie.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RunningTimeTest {
    @Test
    fun `영화 상영시간이 최소 1분 미만이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { RunningTime(0) }
    }
}
