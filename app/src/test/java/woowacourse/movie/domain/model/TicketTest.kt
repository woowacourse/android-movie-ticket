package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

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
    fun `티켓 개수를 1 증가시킨다`() {
        val ticket = Ticket(1)
        val increasedTicket = ticket.increase()

        assertThat(increasedTicket).isEqualTo(Ticket(2))
    }

    @Test
    fun `티켓 개수를 1 감소시킨다`() {
        val ticket = Ticket(3)
        val increasedTicket = ticket.decrease()

        assertThat(increasedTicket).isEqualTo(Ticket(2))
    }
}
