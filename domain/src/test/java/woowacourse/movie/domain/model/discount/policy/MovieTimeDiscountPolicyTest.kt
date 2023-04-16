package woowacourse.movie.domain.model.discount.policy

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.model.movie.MovieTime

internal class MovieTimeDiscountPolicyTest {
    private lateinit var movieDayDiscountPolicy: DiscountPolicy

    @Before
    internal fun setUp() {
        movieDayDiscountPolicy = MovieTimeDiscountPolicy(MovieTime(10, 0))
    }

    @Test
    internal fun `무비 타임 정책은 2000원 할인을 적용한다`() {
        val actual = movieDayDiscountPolicy.discount(13_000)
        val expected = 11_000

        assertEquals(expected, actual)
    }
}
