package woowacourse.movie.seat

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.seat.SeatPricingPolicy

class SeatPricingPolicyTest {
    private lateinit var pricingPolicy: SeatPricingPolicy
    private lateinit var selectedSeats: List<String>

    @BeforeEach
    fun setUp() {
        selectedSeats = listOf("A1", "C1")
        pricingPolicy = SeatPricingPolicy(selectedSeats)
    }

    @Test
    fun `각 좌석의 요금의 합을 반환한다`() {
        // When
        val amount = pricingPolicy.calculatePrice()

        // Then
        amount shouldBe 25000
    }
}
