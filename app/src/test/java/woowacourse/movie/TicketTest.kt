package woowacourse.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import woowacourse.movie.domain.Ticket

class TicketTest {
    @Test
    fun `티켓은 1장 이상이다`() {
        assertThrows(IllegalArgumentException::class.java) {
            Ticket(0)
        }
    }

    @Test
    fun `티켓을 하나 감소한다`() {
        var ticket = Ticket(5)
        val actual = --ticket
        val expected = Ticket(4)

        assertEquals(actual, expected)
    }

    @Test
    fun `티켓을 하나 증가한다`() {
        var ticket = Ticket(5)
        val actual = ++ticket
        val expected = Ticket(6)

        assertEquals(actual, expected)
    }

    @Test
    fun `티켓 개수만큼 총 가격을 계산한다`() {
        val ticket = Ticket(5)
        val actual = ticket.calculateTotalPrice(13000)
        val expected = 65000

        assertEquals(actual, expected)
    }
}
