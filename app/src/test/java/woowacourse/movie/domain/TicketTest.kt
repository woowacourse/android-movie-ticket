package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class TicketTest {

    @Test
    fun `인원 수가 3명일 때 티켓 값은 39000원이다`() {
        val ticket = Ticket(13000, LocalDate.of(2024,4, 1), "해리포터", 3)
        val expect = 39000

        assertEquals(ticket.calculateTicketPrice(), expect)
    }

}
