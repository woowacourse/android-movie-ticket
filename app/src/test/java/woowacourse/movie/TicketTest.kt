package woowacourse.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import woowacourse.movie.domain.MovieDate
import woowacourse.movie.domain.MovieTime
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.policy.DiscountDecorator

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

        assertEquals(expected, actual)
    }

    @Test
    fun `티켓을 하나 증가한다`() {
        var ticket = Ticket(5)
        val actual = ++ticket
        val expected = Ticket(6)

        assertEquals(expected, actual)
    }

    @Test
    fun `티켓이 두 개일 때, 무비데이고 조조 시간대면 티켓당 10% 할인과 2_000원 할인이 적용된다`() {
        val ticket = Ticket(2)
        val movieDate = MovieDate(2023, 4, 10)
        val movieTime = MovieTime(9, 0)

        val actual = ticket.calculateTotalPrice(DiscountDecorator(movieDate, movieTime))
        val expected = 19_400

        assertEquals(expected, actual)
    }
}
