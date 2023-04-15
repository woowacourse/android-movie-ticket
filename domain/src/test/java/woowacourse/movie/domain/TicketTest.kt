package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import woowacourse.movie.domain.model.ticket.Ticket

class TicketTest {
    @Test
    fun `티켓은 최소 1장 이상이다`() {
        assertThrows(IllegalArgumentException::class.java) {
            Ticket(0)
        }
    }

    @Test
    fun `티켓을 하나 감소한다`() {
        val ticket = Ticket(5)
        val actual = ticket - 1
        val expected = Ticket(4)

        assertEquals(expected, actual)
    }

    @Test
    fun `티켓을 하나 증가한다`() {
        val ticket = Ticket(5)
        val actual = ticket + 1
        val expected = Ticket(6)

        assertEquals(expected, actual)
    }
//
//    @Test
//    fun `티켓이 두 개일 때, 무비데이고 조조 시간대면 티켓당 10% 할인과 2_000원 할인이 적용된다`() {
//        val ticket = Ticket(2)
//        val movieDate = MovieDate(2023, 4, 10)
//        val movieTime = MovieTime(9, 0)
//
//        val actual = ticket.applyDiscountPolicy(listOf(movieDate, movieTime), 13_000)
//        val expected = 19_400
//
//        assertEquals(expected, actual)
//    }

//    @Test
//    fun `티켓이 두 개일 때, 무비데이면 티켓당 10% 할인이 적용된다`() {
//        val ticket = Ticket(2)
//        val movieDate = MovieDate(2023, 4, 20)
//        val movieTime = MovieTime(12, 0)
//
//        val actual = ticket.applyDiscountPolicy(listOf(movieDate, movieTime), 13_000)
//        val expected = 23_400
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `티켓이 두 개일 때, 조조 시간대면 티켓당 2_000원 할인이 적용된다`() {
//        val ticket = Ticket(2)
//        val movieDate = MovieDate(2023, 4, 11)
//        val movieTime = MovieTime(9, 0)
//
//        val actual = ticket.applyDiscountPolicy(listOf(movieDate, movieTime), 13_000)
//        val expected = 22_000
//
//        assertEquals(expected, actual)
//    }
}
