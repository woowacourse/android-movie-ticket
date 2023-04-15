package woowacourse.movie.policy

import junit.framework.TestCase
import org.junit.Test
import woowacourse.movie.domain.MovieDate
import woowacourse.movie.domain.policy.MovieDayPolicy

class MovieDayPolicyTest {
    @Test
    fun `무비 데이고, 영화 가격이 13_000원일 때, 영화 가격은 10% 할인 된 11_700원이다`() {
        val movieDate = MovieDate(2023, 4, 20)
        val movieDay = MovieDayPolicy(movieDate)
        val ticketPrice = 13_000
        val actual = movieDay.calculatePrice(ticketPrice)
        val expected = 11_700
        TestCase.assertEquals(expected, actual)
    }
}
