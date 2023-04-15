package woowacourse.movie.policy

import junit.framework.TestCase
import org.junit.Test
import woowacourse.movie.domain.MovieTime
import woowacourse.movie.domain.policy.EarlyAndLatePolicy

class EarlyAndLatePolicyTest {
    @Test
    fun `조조 영화이고, 영화 가격이 13_000원일 때, 영화 가격은 2_000원 할인 된 11_000원이다`() {
        val movieTime = MovieTime(9)
        val earlyAndLatePolicy = EarlyAndLatePolicy(movieTime)
        val ticketPrice = 13_000
        val actual = earlyAndLatePolicy.calculatePrice(ticketPrice)
        val expected = 11_000
        TestCase.assertEquals(expected, actual)
    }
}
