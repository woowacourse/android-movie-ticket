package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RunningTimeTest {
    @Test
    fun `초기 상영 시간을 지정할 수 있다`() {
        val runningTime = RunningTime(120)
        val expected = 120
        assertThat(runningTime.minute).isEqualTo(expected)
    }

    @Test
    fun `상영 시간이 최소 상영 시간보다 작으면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            RunningTime(0)
        }
    }
}
