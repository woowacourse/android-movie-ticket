package woowacourse.movie.model.pricing

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Quantity
import woowacourse.movie.model.TestFixture.screeningBuilder

class UniformPricingSystemTest {
    private lateinit var uniformPricingSystem: UniformPricingSystem

    @Test
    fun `기본 가격이 10000이고 수량이 3일 때 30000을 반환한다`() {
        uniformPricingSystem = UniformPricingSystem(10000)
        val quantity = Quantity(3)
        val result = uniformPricingSystem.calculatePrice(screeningBuilder(), quantity)
        assertThat(result).isEqualTo(30000)
    }

    @Test
    fun `기본 가격이 13000이고 수량이 10일 때 130000 반환한다`() {
        uniformPricingSystem = UniformPricingSystem(13000)
        val quantity = Quantity(10)
        val result = uniformPricingSystem.calculatePrice(screeningBuilder(), quantity)
        assertThat(result).isEqualTo(130000)
    }
}
