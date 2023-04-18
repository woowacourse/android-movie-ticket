package woowacourse.movie.domain


import domain.policy.MovieDayDiscountPolicy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MovieDayDiscountPolicyTest {
    @Test
    fun `무비데이라면 true를 반환한다`() {
        val date = LocalDateTime.of(2023, 4, 10, 15, 10)
        assertTrue(MovieDayDiscountPolicy().checkPolicy(date))
    }

    @Test
    fun `티켓 값이 13000원이면, 무비데이 할인 정책이 적용하여 11700원을 반환한다`() {
        val price = 13000
        assertEquals(MovieDayDiscountPolicy().discountPrice(price), 11700)
    }
}
