package woowacourse.movie.domain.price

import org.junit.Assert.assertEquals
import org.junit.Test

class PricePolicyCalculatorTest {
    @Test
    fun `할인이 없는경우 13000원짜리 표를 구매하면 13000원이다`() {
        val pricePolicy = woowacourse.movie.domain.price.pricecalculate.PricePolicyCalculator()
        assertEquals(
            pricePolicy.discountCalculate(
                TicketPrice(13000)
            ),
            TicketPrice(13000)
        )
    }
}
