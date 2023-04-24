package domain.seat

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ScreeningSeatTest(
    private val seat: ScreeningSeat,
    private val seatRate: SeatRate
) {

    @Test
    fun `A_B열은_B등급_C_D열은_S등급_E_열은_A등급이다`() {
        assertEquals(seat.rate, seatRate)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Any>> = listOf(
            arrayOf(ScreeningSeat.valueOf(SeatRow.A, SeatColumn.FIRST), SeatRate.B),
            arrayOf(ScreeningSeat.valueOf(SeatRow.B, SeatColumn.FIRST), SeatRate.B),
            arrayOf(ScreeningSeat.valueOf(SeatRow.C, SeatColumn.FIRST), SeatRate.S),
            arrayOf(ScreeningSeat.valueOf(SeatRow.D, SeatColumn.FIRST), SeatRate.S),
            arrayOf(ScreeningSeat.valueOf(SeatRow.E, SeatColumn.FIRST), SeatRate.A),
        )
    }
}
