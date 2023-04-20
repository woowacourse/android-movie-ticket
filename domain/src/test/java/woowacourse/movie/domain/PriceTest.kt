package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PriceTest {
    @Test
    fun `1000원에 1000원을 더하면 2000원이다`() {
        val price = Price(1000)

        price.plus(1000)

        assertThat(price.amount).isEqualTo(2000)
    }

    @Test
    fun `1000원에 1000원을 빼면 0원이다`() {
        val price = Price(1000)

        price.minus(1000)

        assertThat(price.amount).isEqualTo(0)
    }
}
