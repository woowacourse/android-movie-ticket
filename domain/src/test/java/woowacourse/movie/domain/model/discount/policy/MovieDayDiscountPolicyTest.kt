package woowacourse.movie.domain.model.discount.policy

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.model.movie.MovieDate

internal class MovieDayDiscountPolicyTest {
    private lateinit var movieDayDiscountPolicy: DiscountPolicy

    @Before
    internal fun setUp() {
        movieDayDiscountPolicy = MovieDayDiscountPolicy(MovieDate(2023, 10, 10))
    }

    @Test
    internal fun `무비 데이 정책은 10프로 할인을 적용한다`() {
        val actual = movieDayDiscountPolicy.discount(13_000)
        val expected = 11_700

        assertEquals(expected, actual)
    }
}
