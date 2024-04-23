package woowacourse.movie.model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HeadCountTest {
    @Test
    fun `HeadCount는 1 이상이어야 한다`() {
        assertSoftly {
            shouldThrow<IllegalArgumentException> {
                HeadCount(0)
            }
            shouldNotThrow<IllegalArgumentException> {
                HeadCount(1)
            }
        }
    }

    @Test
    fun `HeadCount 는 1씩 증가할 수 있다`() {
        // given
        val headCount = HeadCount(1)
        val expected = HeadCount(2)
        // when
        val actual = headCount.increase()
        // then
        actual shouldBe expected
    }

    @Test
    fun `HeadCount는 1씩 감소할 수 있다`() {
        // given
        val headCount = HeadCount(3)
        val expected = HeadCount(2)
        // when
        val actual = headCount.decrease()
        // then
        actual shouldBe expected
    }
}
