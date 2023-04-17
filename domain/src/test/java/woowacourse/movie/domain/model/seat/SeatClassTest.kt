package woowacourse.movie.domain.model.seat

import junit.framework.TestCase.assertEquals
import org.junit.Test
import woowacourse.movie.domain.model.movie.TicketPrice

internal class SeatClassTest {
    @Test
    internal fun `S등급은 15_000원이다`() {
        val seatClass = SeatClass.S
        val expected = seatClass.ticketPrice
        val actual = TicketPrice(15_000)

        assertEquals(expected, actual)
    }

    @Test
    internal fun `A등급은 12_000원이다`() {
        val seatClass = SeatClass.A
        val expected = seatClass.ticketPrice
        val actual = TicketPrice(12_000)

        assertEquals(expected, actual)
    }

    @Test
    internal fun `B등급은 10_000원이다`() {
        val seatClass = SeatClass.B
        val expected = seatClass.ticketPrice
        val actual = TicketPrice(10_000)

        assertEquals(expected, actual)
    }
}
