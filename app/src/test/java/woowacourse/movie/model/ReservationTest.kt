package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.TestFixture.reservationBuilder
import woowacourse.movie.model.pricing.UniformPricingSystem

class ReservationTest {
    @Test
    fun `티켓의 가격이 13_000원일 때 3장을 구매하면 총 가격은 39,000원이다`() {
        val ticketQuantity = 3
        val pricePerTicket = 13_000
        val reservation = reservationBuilder(ticketQuantity, UniformPricingSystem(pricePerTicket))
        val result = reservation.price
        assertThat(result).isEqualTo(39_000)
    }

    @Test
    fun `티켓의 가격이 10_000원일 때 5장을 구매하면 총 가격은 50_000원이다`() {
        val ticketQuantity = 5
        val pricePerTicket = 10_000
        val reservation = reservationBuilder(ticketQuantity, UniformPricingSystem(pricePerTicket))
        val result = reservation.price
        assertThat(result).isEqualTo(50000)
    }
}
