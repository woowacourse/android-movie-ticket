package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test

class TicketTest {

    @Test
    fun `증가하면 티켓 예약자수가 한 명 증가한다`() {
        var ticket = Ticket(1)
        ticket = ticket.increase()

        assertEquals(ticket.numberOfPeople, 2)
    }

    @Test
    fun `예약자 수가 최대일 때 증가하면 최대를 넘어가지 않는다`() {
        var ticket = Ticket(10)
        ticket = ticket.increase()
        assertEquals(ticket.numberOfPeople, 10)
    }

    @Test
    fun `예약자 수가 최소일 때 감소하면 최소를 넘어가지 않는다`() {
        var ticket = Ticket(1)
        ticket = ticket.decrease()
        assertEquals(ticket.numberOfPeople, 1)
    }

    @Test
    fun `감소하면 티켓 예약자수가 한 명 감소한다`() {
        var ticket = Ticket(2)
        ticket = ticket.decrease()
        assertEquals(ticket.numberOfPeople, 1)
    }
}

