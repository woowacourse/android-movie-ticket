package woowacourse.movie.domain.price

import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDayDiscountTest {
    @Test
    fun `할인전 금액이 13000원인 경우 무비데이할인을 적용하면 11700원을 반환한다`() {
        assertEquals(MovieDayDiscount().discount(13000), 11700)
    }
}
