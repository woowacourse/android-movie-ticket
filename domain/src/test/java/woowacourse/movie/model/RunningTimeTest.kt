package woowacourse.movie.model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.minutes

class RunningTimeTest {
    @Test
    fun `상영 시간은 30분 이상이어야 한다`() {
        assertSoftly {
            shouldThrow<IllegalArgumentException> {
                RunningTime(29.minutes)
            }
            shouldNotThrow<IllegalArgumentException> {
                RunningTime(30.minutes)
            }
        }
    }
}
