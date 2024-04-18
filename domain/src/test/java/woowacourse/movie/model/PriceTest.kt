package woowacourse.movie.model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PriceTest {
    @Test
    fun `가격은 0원 이상이어야 한다`() {
        assertSoftly {
            shouldThrow<IllegalArgumentException> {
                Price(-1)
            }
            shouldNotThrow<IllegalArgumentException> {
                Price(0)
            }
        }
    }

    @Test
    fun `1개당 1000원인 상품을 3개 구매하면 3000원이다`() {
        // given
        val price = Price(1000)
        val expected = Price(3000)
        // when
        val count = 3
        val actual = price * count
        // then
        actual shouldBe expected
    }
}
