package movie

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TicketQuantityTest {
    @Test
    fun `티켓 수는 0보다 커야 한다`() {
        assertThrows<IllegalArgumentException> {
            TicketQuantity(0)
        }
    }

    @Test
    fun `티켓 수는 1씩 증가 한다`() {
        val ticketQuantity = TicketQuantity(1)
        assertEquals(TicketQuantity(2), ticketQuantity.inc())
    }

    @Test
    fun `티켓 수는 1씩 감소 한다`() {
        val ticketQuantity = TicketQuantity(2)
        assertEquals(TicketQuantity(1), ticketQuantity.dec())
    }

    @Test
    fun `티켓 수는 1보다 작아지지 않는다`() {
        val ticketQuantity = TicketQuantity(1)
        assertEquals(TicketQuantity(1), ticketQuantity.dec())
    }

    @Test
    fun `티켓 수는 정수 문자열로 변환 된다`() {
        val ticketQuantity = TicketQuantity(1)
        assertEquals("1", ticketQuantity.toString())
    }

    @Test
    fun `티켓 수는 정수로 변환 된다`() {
        val ticketQuantity = TicketQuantity(1)
        assertEquals(1, ticketQuantity.toInt())
    }
}
