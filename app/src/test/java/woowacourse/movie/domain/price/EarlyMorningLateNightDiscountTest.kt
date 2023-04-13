package woowacourse.movie.domain.price

import org.junit.Assert.assertEquals
import org.junit.Test

class EarlyMorningLateNightDiscountTest {
    @Test
    fun `할인전 금액이 13000원인 경우 조조,심야할인을 적용하면 11000원을 반환한다`() {
        assertEquals(EarlyMorningLateNightDiscount().discount(13000), 11000)
    }
}
