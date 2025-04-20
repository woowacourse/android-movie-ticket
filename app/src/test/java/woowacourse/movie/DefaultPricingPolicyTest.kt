package woowacourse.movie

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.model.DefaultPricingPolicy

class DefaultPricingPolicyTest {
    @Test
    fun `기본 요금 정책은 1인당 13000원을 부과한다`() {
        // Given
        val defaultPricingPolicy = DefaultPricingPolicy()

        // When
        val amount = defaultPricingPolicy.calculatePrice(1)

        // Then
        amount shouldBe 13000
    }
}