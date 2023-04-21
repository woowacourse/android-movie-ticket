package woowacourse.movie.domain.price

import org.junit.Assert.assertEquals
import org.junit.Test

class PricePolicyCalculatorTest {
    @Test
    fun `할인이 없는경우 13000원짜리 표 3장을 구매하면 39000원이다`() {
        val pricePolicy = woowacourse.movie.domain.price.pricecalculate.PricePolicyCalculator()
        assertEquals(
            pricePolicy.totalPriceCalculate(
                TicketPrice(13000),
                TicketCount(3)
            ),
            TicketPrice(39000)
        )
    }
}
