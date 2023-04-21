package woowacourse.movie.domain.price

import org.junit.Assert.assertEquals
import org.junit.Test
import woowacourse.movie.domain.price.pricecalculate.PricePolicyCalculator

class PricePolicyCalculatorTest {
    @Test
    fun `할인이 없는경우 13000원짜리 표 3장을 구매하면 39000원이다`() {
        val pricePolicy = woowacourse.movie.domain.price.pricecalculate.PricePolicyCalculator()
        assertEquals(
            pricePolicy.totalPriceCalculate(
                woowacourse.movie.domain.price.TicketPrice(13000),
                woowacourse.movie.domain.price.TicketCount(3)
            ),
            woowacourse.movie.domain.price.TicketPrice(39000)
        )
    }
}
