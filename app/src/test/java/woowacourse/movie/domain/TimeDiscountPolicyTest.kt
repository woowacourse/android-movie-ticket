package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import woowacourse.movie.domain.policy.TimeDiscountPolicy
import java.time.LocalDateTime

class TimeDiscountPolicyTest {

    @Test
    fun `야간 시간이라면 true를 반환한다`() {
        val date = LocalDateTime.of(2023, 4, 1, 20, 10)
        assertTrue(TimeDiscountPolicy().checkPolicy(date))
    }

    @Test
    fun `티켓 값이 13000원이면, 시간 할인 정책이 적용하여 11000원을 반환한다`() {
        val price = 13000
        assertEquals(TimeDiscountPolicy().discountPrice(price), 11000)
    }
}
