package domain.seat

import domain.payment.PaymentAmount
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ScreeningSeatTest {

    @Test
    fun `A열_위치를_가지는_좌석들은_B등급이다`() {
        // given
        val seat = ScreeningSeat.valueOf(SeatRow.A, SeatColumn.THIRD)

        // when
        val paymentAmount = seat.paymentAmount

        // then
        val expected = PaymentAmount(10000)
        assertEquals(paymentAmount, expected)
    }

    @Test
    fun `B열_위치를_가지는_좌석들은_B등급이다`() {
        // given
        val seat = ScreeningSeat.valueOf(SeatRow.B, SeatColumn.THIRD)

        // when
        val seatRate = seat.rate

        // then
        val expected = SeatRate.A
        assertEquals(seatRate, expected)
    }

    @Test
    fun `C열_위치를_가지는_좌석들은_S등급이다`() {
        // given
        val seat = ScreeningSeat.valueOf(SeatRow.C, SeatColumn.THIRD)

        // when
        val seatRate = seat.rate

        // then
        val expected = SeatRate.S
        assertEquals(seatRate, expected)
    }

    @Test
    fun `D열_위치를_가지는_좌석들은_S등급이다`() {
        // given
        val seat = ScreeningSeat.valueOf(SeatRow.D, SeatColumn.THIRD)

        // when
        val seatRate = seat.rate

        // then
        val expected = SeatRate.S
        assertEquals(seatRate, expected)
    }

    @Test
    fun `E열_위치를_가지는_좌석들은_A등급이다`() {
        // given
        val seat = ScreeningSeat.valueOf(SeatRow.E, SeatColumn.THIRD)

        // when
        val seatRate = seat.rate

        // then
        val expected = SeatRate.A
        assertEquals(seatRate, expected)
    }
}
