package woowacourse.movie.domain.model.ticket

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class TicketTest {
    @Test
    fun `티켓은 최소 1장 이상이다`() {
        assertThrows(IllegalArgumentException::class.java) {
            Ticket(0)
        }
    }

    @Test
    fun `티켓을 하나 감소한다`() {
        val ticket = Ticket(5)
        val actual = ticket - 1
        val expected = Ticket(4)

        assertEquals(expected, actual)
    }

    @Test
    fun `티켓을 하나 증가한다`() {
        val ticket = Ticket(5)
        val actual = ticket + 1
        val expected = Ticket(6)

        assertEquals(expected, actual)
    }
}
