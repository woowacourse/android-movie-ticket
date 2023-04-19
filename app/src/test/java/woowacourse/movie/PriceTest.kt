package woowacourse.movie

import org.junit.Test
import woowacourse.movie.model.Price

class PriceTest {

    @Test(expected = IllegalArgumentException::class)
    fun `영화 가격은 0원 이상이어야 한다`() {
        Price(-1)
    }
}
