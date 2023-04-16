package woowacourse.movie.domain.model.ticket

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class TicketTest {
    @Test
    @Parameters("0", "101")
    internal fun `티켓이 1장 미만, 100장 초과이면 예외가 발생한다`(ticketCount: Int) {
        assertThrows(IllegalArgumentException::class.java) {
            Ticket(ticketCount)
        }
    }

    @Test
    @Parameters("1", "100")
    internal fun `티켓은 최소 1장, 최대 100장이다`(ticketCount: Int) {
        Ticket(ticketCount)
    }

    @Test
    internal fun `티켓을 하나 감소한다`() {
        val ticket = Ticket(5)
        val actual = ticket - 1
        val expected = Ticket(4)

        assertEquals(expected, actual)
    }

    @Test
    internal fun `티켓을 하나 증가한다`() {
        val ticket = Ticket(5)
        val actual = ticket + 1
        val expected = Ticket(6)

        assertEquals(expected, actual)
    }
}
