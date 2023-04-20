package movie.domain.price

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PricePolicyCalculatorTest {

    @Test
    fun `할인이 없는경우 13000원짜리 표 3장을 구매하면 39000원이다`() {
        val pricePolicy = PricePolicyCalculator(bookedScreeningDateTimeState.toDomain())
        assertThat(pricePolicy.totalPriceCalculate(13000, 3)).isEqualTo(39000)
    }
}
