package movie.domain.price

import movie.domain.seat.Seat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PricePolicyCalculatorTest {

    @Test
    fun `할인이 없는경우 10000원짜리 표 3장을 구매하면 39000원이다`() {
        val seat = listOf(Seat(0))
        val pricePolicy = PricePolicyCalculator(listOf())
        assertThat(pricePolicy.totalPriceCalculate(seat)).isEqualTo(10000)
    }
}
