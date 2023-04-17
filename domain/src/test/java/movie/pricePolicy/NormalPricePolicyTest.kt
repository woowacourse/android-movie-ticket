package movie.pricePolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class NormalPricePolicyTest {
    @Test
    fun `무비날 조조 시간대면 무비날 할인이 먼저다`() {
        // given
        val normalPricePolicy = NormalPricePolicy()
        val localDateTime = LocalDateTime.of(2021, 1, 10, 6, 0)
        val pricePolicyInfo = PricePolicyInfo(10000, localDateTime)

        // when
        val price = normalPricePolicy.calculatePrice(pricePolicyInfo)

        // then
        assertThat(price.price).isEqualTo(7000)
    }

    @Test
    fun `무비날 야간 시간대면 야간 할인이 먼저다`() {
        // given
        val normalPricePolicy = NormalPricePolicy()
        val localDateTime = LocalDateTime.of(2021, 1, 10, 23, 0)
        val pricePolicyInfo = PricePolicyInfo(10000, localDateTime)

        // when
        val price = normalPricePolicy.calculatePrice(pricePolicyInfo)

        // then
        assertThat(price.price).isEqualTo(7000)
    }
}
