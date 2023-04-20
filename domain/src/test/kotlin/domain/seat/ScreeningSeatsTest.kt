package domain.seat

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ScreeningSeatsTest {

    @Test
    fun `좌석이_선택되면_해당_좌석은_선택된_상태가_된다`() {
        // given
        val screeningSeats = ScreeningSeats()

        // when
        val seat = ScreeningSeat.valueOf(SeatRow.A, SeatColumn.FIRST)
        screeningSeats.selectSeat(seat)

        // then
        assertTrue(
            screeningSeats.values[seat] == SeatState.SELECTED
        )
    }

    @Test
    fun `이용가능한_좌석을_선택하면_해당_좌석을_반환한다`() {
        // given
        val screeningSeats = ScreeningSeats()
        val seat = ScreeningSeat.valueOf(SeatRow.A, SeatColumn.FIRST)

        // when
        val selectedSeat = screeningSeats.selectSeat(seat)
        val expected = ScreeningSeat.valueOf(SeatRow.A, SeatColumn.FIRST)

        // then
        assertEquals(expected, selectedSeat)
    }

    @Test
    fun `이미_선택된_좌석을_선택하면_null을_반환한다`() {
        // given
        val screeningSeats = ScreeningSeats()
        val seat = ScreeningSeat.valueOf(SeatRow.A, SeatColumn.FIRST)
        screeningSeats.selectSeat(seat)

        // when
        val selectedSeat = screeningSeats.selectSeat(seat)
        val expected = null

        // then
        assertEquals(expected, selectedSeat)
    }

    @Test
    fun `선택된_좌석을_취소하면_해당_좌석은_이용가능한_상태가_된다`() {
        // given
        val screeningSeats = ScreeningSeats()

        // when
        val seat = ScreeningSeat.valueOf(SeatRow.A, SeatColumn.FIRST)
        screeningSeats.cancelSeat(seat)

        // then
        assertTrue(screeningSeats.values[seat] == SeatState.AVAILABLE)
    }

    @Test
    fun `선택된_좌석을_취소하면_해당_좌석을_반환한다`() {
        // given
        val screeningSeats = ScreeningSeats()
        val seat = ScreeningSeat.valueOf(SeatRow.A, SeatColumn.FIRST)
        screeningSeats.selectSeat(seat)

        // when
        val canceledSeat = screeningSeats.cancelSeat(seat)
        val expected = ScreeningSeat.valueOf(SeatRow.A, SeatColumn.FIRST)

        // then
        assertEquals(expected, canceledSeat)
    }

    @Test
    fun `이용가능한_좌석을_취소하면_null을_반환한다`() {
        // given
        val screeningSeats = ScreeningSeats()

        // when
        val canceledSeat =
            screeningSeats.cancelSeat(ScreeningSeat.valueOf(SeatRow.A, SeatColumn.FIRST))
        val expected = null

        // then
        assertEquals(expected, canceledSeat)
    }
}
