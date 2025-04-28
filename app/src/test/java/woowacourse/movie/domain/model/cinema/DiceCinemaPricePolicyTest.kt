package woowacourse.movie.domain.model.cinema

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.cinema.screen.SeatType

class DiceCinemaPricePolicyTest {
    private val pricePolicy = DiceCinemaPricePolicy()

    @Nested
    @DisplayName("calculatePrice 함수는")
    inner class CalculatePrice {
        @Test
        @DisplayName("S_CLASS 좌석이면 15,000원을 반환한다")
        fun `S_CLASS 좌석 가격`() {
            // when
            val price = pricePolicy.calculatePrice(SeatType.S_CLASS)

            // then
            assertEquals(15_000, price)
        }

        @Test
        @DisplayName("A_CLASS 좌석이면 12,000원을 반환한다")
        fun `A_CLASS 좌석 가격`() {
            // when
            val price = pricePolicy.calculatePrice(SeatType.A_CLASS)

            // then
            assertEquals(12_000, price)
        }

        @Test
        @DisplayName("B_CLASS 좌석이면 10,000원을 반환한다")
        fun `B_CLASS 좌석 가격`() {
            // when
            val price = pricePolicy.calculatePrice(SeatType.B_CLASS)

            // then
            assertEquals(10_000, price)
        }
    }
}
