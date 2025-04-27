package woowacourse.movie.policy

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.policy.PricingPolicy

class PricingPolicyTest {
    private lateinit var pricingPolicy: PricingPolicy
    private lateinit var selectedSeats: List<String>

    @BeforeEach
    fun setUp() {
        selectedSeats = listOf("A1", "C1")
        pricingPolicy = PricingPolicy(selectedSeats)
    }

    @Test
    fun `각 좌석의 요금의 합을 반환한다`() {
        // When
        val amount = pricingPolicy.calculatePrice()

        // Then
        amount shouldBe 25000
    }
}
