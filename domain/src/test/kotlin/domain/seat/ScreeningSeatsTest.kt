package domain.seat

import domain.payment.PaymentAmount
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ScreeningSeatsTest {

    @Test
    fun `좌석이_선택되면_해당_좌석은_선택된_상태가_된다`() {
        // given
        val screeningSeats = ScreeningSeats()

        // when
        val seat = ScreeningSeat(SeatRow.A, SeatColumn.FIRST)
        screeningSeats.selectSeat(seat)

        // then
        assertTrue(
            screeningSeats.values[seat] == SeatState.RESERVED
        )
    }

    @Test
    fun `선택된_좌석을_취소하면_해당_좌석은_이용_가능한_상태가_된다`() {
        // given
        val screeningSeats = ScreeningSeats()

        // when
        val seat = ScreeningSeat(SeatRow.A, SeatColumn.FIRST)
        screeningSeats.cancelSeat(seat)

        // then
        assertTrue(
            screeningSeats.values[seat] == SeatState.AVAILABLE
        )
    }

    @Test
    fun `선택한_좌석들의_총_가격을_반환한다`() {
        // given
        val screeningSeats = ScreeningSeats()

        // when
        screeningSeats.selectSeat(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))
        screeningSeats.selectSeat(ScreeningSeat(SeatRow.B, SeatColumn.FIRST))
        screeningSeats.selectSeat(ScreeningSeat(SeatRow.E, SeatColumn.FIRST))

        // then
        val expected = PaymentAmount(32000)

        assertEquals(expected, screeningSeats.getTotalPaymentAmount())
    }
}
