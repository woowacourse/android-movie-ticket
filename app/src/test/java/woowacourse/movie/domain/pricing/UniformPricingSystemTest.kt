package woowacourse.movie.domain.pricing

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.TestFixture.MOCK_SCREENING
import woowacourse.movie.domain.reservation.Quantity

class UniformPricingSystemTest {
    private lateinit var uniformPricingSystem: UniformPricingSystem

    @Test
    fun `기본 가격이 10000이고 수량이 3일 때 30000을 반환한다`() {
        uniformPricingSystem = UniformPricingSystem(10000)
        val quantity = Quantity(3)
        val result = uniformPricingSystem.calculatePrice(MOCK_SCREENING, quantity)
        assertThat(result).isEqualTo(30000)
    }

    @Test
    fun `기본 가격이 13000이고 수량이 10일 때 130000 반환한다`() {
        uniformPricingSystem = UniformPricingSystem(13000)
        val quantity = Quantity(10)
        val result = uniformPricingSystem.calculatePrice(MOCK_SCREENING, quantity)
        assertThat(result).isEqualTo(130000)
    }
}
