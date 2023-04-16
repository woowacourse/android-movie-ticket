package woowacourse.movie.domain.model.movie

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.model.discount.policy.MovieDayDiscountPolicy
import woowacourse.movie.domain.model.discount.policy.MovieTimeDiscountPolicy

internal class TicketPriceTest {
    private lateinit var movieTimeDiscountPolicy: MovieTimeDiscountPolicy
    private lateinit var movieDayDiscountPolicy: MovieDayDiscountPolicy

    @Before
    internal fun setup() {
        movieTimeDiscountPolicy = MovieTimeDiscountPolicy(MovieTime(10, 0))
        movieDayDiscountPolicy = MovieDayDiscountPolicy(MovieDate(2023, 10, 10))
    }

    @Test
    internal fun `할인 시간 정책에 따라 할인된 티켓 가격을 반환한다`() {
        val ticketPrice = TicketPrice(13_000)
        val actual = ticketPrice.applyDiscountPolicy(movieTimeDiscountPolicy)
        val expected = TicketPrice(11_000)

        assertEquals(actual, expected)
    }

    @Test
    internal fun `무비 데이 정책에 따라 할인된 티켓 가격을 반환한다`() {
        val ticketPrice = TicketPrice(13_000)
        val actual = ticketPrice.applyDiscountPolicy(movieDayDiscountPolicy)
        val expected = TicketPrice(11_700)

        assertEquals(actual, expected)
    }

    @Test
    internal fun `무비 데이, 할인 시간 순으로 정책을 적용하여 할인된 티켓 가격을 반환한다`() {
        val ticketPrice = TicketPrice(13_000)
        val actual = ticketPrice.applyDiscountPolicy(
            movieDayDiscountPolicy, movieTimeDiscountPolicy
        )
        val expected = TicketPrice(9_700)

        assertEquals(actual, expected)
    }
}
