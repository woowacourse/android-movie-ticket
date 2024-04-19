package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.TestFixture.reservationBuilder
import woowacourse.movie.model.pricing.UniformPricingSystem

class ReservationTest {
    @Test
    fun `예약은 스스로 총 가격을 계산할 수 있다`() {
        val ticketQuantity = 3
        val pricePerTicket = 10000
        val reservation = reservationBuilder(ticketQuantity, UniformPricingSystem(pricePerTicket))
        val result = reservation.price
        assertThat(result).isEqualTo(30000)
    }
}
