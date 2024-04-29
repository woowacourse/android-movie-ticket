package woowacourse.movie.basic.presentation.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import woowacourse.movie.presentation.model.Ticket

class TicketTest {
    @Test
    fun `티켓 개수가 1일 때 유효하다`() {
        val ticket = Ticket(1)
        assertFalse(ticket.isInvalidCount())
    }

    @Test
    fun `티켓 개수가 0일 때 유효하지 않다`() {
        val ticket = Ticket(0)
        assertTrue(ticket.isInvalidCount())
    }

    @Test
    fun `티켓 개수가 1일 때 티켓 개수를 더한다`() {
        val ticket = Ticket(1)
        val nextTicket = ticket.increase(1)
        assertThat(nextTicket.count).isEqualTo(2)
    }

    @Test
    fun `티켓 개수가 2일 때 티켓 개수를 뺄 수 있다`() {
        val ticket = Ticket(2)
        val nextTicket = ticket.decrease(1)
        assertThat(nextTicket.count).isEqualTo(1)
    }
}
