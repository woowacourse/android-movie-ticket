package woowacourse.movie.domain.discountPolicy

import org.junit.Assert.assertEquals
import org.junit.Test
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.Ticket
import java.time.LocalDateTime

class MovieDayTest {

    @Test
    fun `무비데이(매월 10,20,30일)인 경우 10% 할인이 적용된다`() {
        // given
        val date = LocalDateTime.of(2023, 1, 10, 0, 0)
        val price = Price(13000)
        val ticket = Ticket(date, 1, DisCountPolicies(listOf(MovieDay(), OffTime())))

        // when
        val actual = MovieDay().discount(ticket, Price(13000)).value

        // then
        val expected = 11700
        assertEquals(actual, expected)
    }

    @Test
    fun `무비데이(매월 10,20,30일)가 아닌 경우 10% 할인이 적용되지 않는다`() {
        // given
        val date = LocalDateTime.of(2023, 1, 11, 0, 0)
        val price = Price(13000)
        val ticket = Ticket(date, 1, DisCountPolicies(listOf(MovieDay(), OffTime())))

        // when
        val actual = MovieDay().discount(ticket, Price()).value

        // then
        val expected = 13000
        assertEquals(actual, expected)
    }
}
