package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class TicketTest {

    @Test
    fun `인원 수가 3명일 때 할인이 적용되지 않은 티켓 값은 39000원이다`() {
        val ticket = Ticket(13000, LocalDateTime.of(2024, 4, 1, 15, 10), "해리포터", 3)
        val expect = 39000

        assertEquals(ticket.calculateTotalPrice(), expect)
    }

    @Test
    fun `인원 수가 3명일 때 조조 할인이 적용된 티켓 값은 33000원이다`() {
        val ticket = Ticket(13000, LocalDateTime.of(2024, 4, 1, 9, 10), "해리포터", 3)
        val expect = 33000

        assertEquals(ticket.calculateTotalPrice(), expect)
    }

    @Test
    fun `인원 수가 3명일 때 무비데이 할인이 적용된 티켓 값은 3000원이다`() {
        val ticket = Ticket(13000, LocalDateTime.of(2024, 4, 10, 15, 10), "해리포터", 3)
        val expect = 35100

        assertEquals(ticket.calculateTotalPrice(), expect)
    }

    @Test
    fun `인원 수가 3명일 때 무비데이 할인과 야간 할인이 적용된 티켓 값은 33000원이다`() {
        val ticket = Ticket(13000, LocalDateTime.of(2024, 4, 10, 21, 10), "해리포터", 3)
        val expect = 29100

        assertEquals(ticket.calculateTotalPrice(), expect)
    }
}
