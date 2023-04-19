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
        screeningSeats.selectSeats(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))

        // then
        assertTrue(
            screeningSeats.selectedSeats.contains(
                ScreeningSeat(
                    SeatRow.A,
                    SeatColumn.FIRST,
                    true
                )
            )
        )
    }

    @Test
    fun `선택된_좌석을_취소하면_해당_좌석은_선택되지_않은_상태가_된다`() {
        // given
        val screeningSeats = ScreeningSeats()
        screeningSeats.selectSeats(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))

        // when
        screeningSeats.cancelSeats(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))

        // then
        assertTrue(
            screeningSeats.values.contains(
                ScreeningSeat(
                    SeatRow.A,
                    SeatColumn.FIRST,
                    false
                )
            )
        )
    }

    @Test
    fun `선택한_좌석들의_총_가격을_반환한다`() {
        // given
        val screeningSeats = ScreeningSeats()

        // when
        screeningSeats.selectSeats(ScreeningSeat(SeatRow.A, SeatColumn.FIRST))
        screeningSeats.selectSeats(ScreeningSeat(SeatRow.B, SeatColumn.FIRST))
        screeningSeats.selectSeats(ScreeningSeat(SeatRow.E, SeatColumn.FIRST))

        // then
        val expected = PaymentAmount(32000)

        assertEquals(expected, screeningSeats.getTotalPaymentAmount())
    }
}
