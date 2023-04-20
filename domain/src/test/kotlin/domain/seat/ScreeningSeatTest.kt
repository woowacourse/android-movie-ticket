package domain.seat

import domain.payment.PaymentAmount
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ScreeningSeatTest {

    @Test
    fun `A행_좌석의_기본_가격은_10000원이다`() {
        // given
        val seat = ScreeningSeat(SeatRow.A, SeatColumn.THIRD)

        // when
        val paymentAmount = seat.paymentAmount

        // then
        val expected = PaymentAmount(10000)
        assertEquals(paymentAmount, expected)
    }

    @Test
    fun `B행_좌석의_기본_가격은_10000원이다`() {
        // given
        val seat = ScreeningSeat(SeatRow.B, SeatColumn.THIRD)

        // when
        val paymentAmount = seat.paymentAmount

        // then
        val expected = PaymentAmount(10000)
        assertEquals(paymentAmount, expected)
    }

    @Test
    fun `C행_좌석의_기본_가격은_10000원이다`() {
        // given
        val seat = ScreeningSeat(SeatRow.C, SeatColumn.THIRD)

        // when
        val paymentAmount = seat.paymentAmount

        // then
        val expected = PaymentAmount(15000)
        assertEquals(paymentAmount, expected)
    }

    @Test
    fun `D행_좌석의_기본_가격은_10000원이다`() {
        // given
        val seat = ScreeningSeat(SeatRow.D, SeatColumn.THIRD)

        // when
        val paymentAmount = seat.paymentAmount

        // then
        val expected = PaymentAmount(15000)
        assertEquals(paymentAmount, expected)
    }

    @Test
    fun `E행_좌석의_기본_가격은_12000원이다`() {
        // given
        val seat = ScreeningSeat(SeatRow.E, SeatColumn.THIRD)

        // when
        val paymentAmount = seat.paymentAmount

        // then
        val expected = PaymentAmount(12000)
        assertEquals(paymentAmount, expected)
    }
}
