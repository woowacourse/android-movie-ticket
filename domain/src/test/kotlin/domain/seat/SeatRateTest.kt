package domain.seat

import domain.payment.PaymentAmount
import org.junit.Assert.assertEquals
import org.junit.Test

class SeatRateTest {

    @Test
    fun `S등급_좌석의_가격은_15000원이다`() {
        // given
        val seatRate = SeatRate.S

        // when
        val paymentAmount = seatRate.getPaymentAmount()

        // then
        val expected = PaymentAmount(15000)
        assertEquals(expected, paymentAmount)
    }

    @Test
    fun `A등급_좌석의_가격은_12000원이다`() {
        // given
        val seatRate = SeatRate.A

        // when
        val paymentAmount = seatRate.getPaymentAmount()

        // then
        val expected = PaymentAmount(12000)
        assertEquals(expected, paymentAmount)
    }

    @Test
    fun `B등급_좌석의_가격은_10000원이다`() {
        // given
        val seatRate = SeatRate.B

        // when
        val paymentAmount = seatRate.getPaymentAmount()

        // then
        val expected = PaymentAmount(10000)
        assertEquals(expected, paymentAmount)
    }
}
