package domain.seat

import org.junit.Assert.assertTrue
import org.junit.Test

class ScreeningSeatsTest {

    @Test
    fun `좌석이_예매되면_해당_좌석은_예약완료_상태가_된다`() {
        // given
        val screeningSeats = ScreeningSeats()

        // when
        val reservedSeats: List<ScreeningSeat> = listOf(
            ScreeningSeat(SeatRow.A, SeatColumn.FIRST),
            ScreeningSeat(SeatRow.D, SeatColumn.SECOND)
        )
        screeningSeats.removeReservedSeats(reservedSeats)

        // then
        assertTrue(
            screeningSeats.values.containsAll(
                listOf(
                    ScreeningSeat(SeatRow.A, SeatColumn.FIRST, true),
                    ScreeningSeat(SeatRow.D, SeatColumn.SECOND, true),
                )
            )
        )
    }
}
