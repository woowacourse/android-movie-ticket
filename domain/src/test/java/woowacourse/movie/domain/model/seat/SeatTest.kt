package woowacourse.movie.domain.model.seat

import junit.framework.TestCase.assertEquals
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class SeatTest {
    @Test
    @Parameters("1", "2")
    internal fun `1, 2행은 B등급이다`(row: Int) {
        val seat = Seat(SeatRow(row), SeatColumn(1))
        val expected = seat.getClass()
        val actual = SeatClass.B

        assertEquals(expected, actual)
    }

    @Test
    @Parameters("3", "4")
    internal fun `3, 4행은 S등급이다`(row: Int) {
        val seat = Seat(SeatRow(row), SeatColumn(1))
        val expected = seat.getClass()
        val actual = SeatClass.S

        assertEquals(expected, actual)
    }

    @Test
    @Parameters("5")
    internal fun `5행은 A등급이다`(row: Int) {
        val seat = Seat(SeatRow(row), SeatColumn(1))
        val expected = seat.getClass()
        val actual = SeatClass.A

        assertEquals(expected, actual)
    }
}
