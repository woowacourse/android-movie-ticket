package woowacourse.movie

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.model.DefaultPricingPolicy

class DefaultPricingPolicyTest {
    @Test
    fun `기본 요금 정책의 티켓 가격은 13000원이다`() {
        // Given
        val headCount = 2
        val defaultPricingPolicy = DefaultPricingPolicy()

        // When
        val amount = defaultPricingPolicy.calculatePrice(headCount)

        // Then
        amount shouldBe 26000
    }
}