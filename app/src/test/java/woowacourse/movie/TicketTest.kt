package woowacourse.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import woowacourse.movie.domain.MovieDate
import woowacourse.movie.domain.MovieTime
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
    fun `티켓이 두 개일 때, 무비데이고 조조 시간대면 티켓당 10% 할인과 2_000원 할인이 적용된다`() {
        val ticket = Ticket(2)
        val movieDate = MovieDate.of(2023, 4, 10)
        val movieTime = MovieTime.of(9, 0)

        val actual = ticket.calculateTotalPrice(13_000, movieDate, movieTime)
        val expected = 19_400

        assertEquals(actual, expected)
    }

    @Test
    fun `티켓이 두 개일 때, 무비데이면 티켓당 10% 할인이 적용된다`() {
        val ticket = Ticket(2)
        val movieDate = MovieDate.of(2023, 4, 20)
        val movieTime = MovieTime.of(12, 0)

        val actual = ticket.calculateTotalPrice(13000, movieDate, movieTime)
        val expected = 23_400

        assertEquals(actual, expected)
    }

    @Test
    fun `티켓이 두 개일 때, 조조 시간대면 티켓당 2_000원 할인이 적용된다`() {
        val ticket = Ticket(2)
        val movieDate = MovieDate.of(2023, 4, 11)
        val movieTime = MovieTime.of(9, 0)

        val actual = ticket.calculateTotalPrice(13_000, movieDate, movieTime)
        val expected = 22_000

        assertEquals(actual, expected)
    }
}
