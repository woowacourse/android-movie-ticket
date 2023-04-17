package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test

class TicketTest {
    @Test
    fun `다른 할인 없이 무비데이라면 10퍼센트 할인한다`() {
        val actual = Ticket().getTicketPrice("2023-04-10", "14:00")
        assertEquals(actual, 11700)
    }

    @Test
    fun `다른 할인 없이 조조 시간이라면 2000원 할인한다`() {
        val actual = Ticket().getTicketPrice("2023-04-09", "10:00")
        assertEquals(actual, 11000)
    }

    @Test
    fun `다른 할인 없이 심야 시간이라면 2000원 할인한다`() {
        val actual = Ticket().getTicketPrice("2023-04-09", "20:00")
        assertEquals(actual, 11000)
    }

    @Test
    fun `할인이 없을시 티켓의 정가를 반환한다`() {
        val actual = Ticket().getTicketPrice("2023-04-09", "19:00")
        assertEquals(actual, 13000)
    }

    @Test
    fun `무비데이 할인과 시간대 할인이 중복된다면 무비데이 할인이 먼저 계산된다`() {
        val actual = Ticket().getTicketPrice("2023-04-10", "10:00")
        assertEquals(actual, 9700)
    }
}
