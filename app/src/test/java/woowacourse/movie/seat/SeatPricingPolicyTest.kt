package woowacourse.movie.seat

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatPricingPolicy

class SeatPricingPolicyTest {
    private lateinit var pricingPolicy: SeatPricingPolicy
    private lateinit var selectedSeats: List<Seat>

    @BeforeEach
    fun setUp() {
        selectedSeats = listOf(Seat(0, 1), Seat(0, 2))
        pricingPolicy = SeatPricingPolicy(selectedSeats)
    }

    @Test
    fun `각 좌석의 요금의 합을 반환한다`() {
        // When
        val amount = pricingPolicy.calculatePrice()

        // Then
        amount shouldBe 20000
    }
}
