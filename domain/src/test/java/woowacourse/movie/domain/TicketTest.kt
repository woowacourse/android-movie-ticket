package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test
import woowacourse.movie.domain.policy.DiscountPolicy
import woowacourse.movie.domain.policy.MorningPolicy
import woowacourse.movie.domain.policy.MovieDayPolicy
import woowacourse.movie.domain.policy.NightPolicy
import woowacourse.movie.domain.ticket.Price
import woowacourse.movie.domain.ticket.Ticket
import java.time.LocalDate
import java.time.LocalTime

class TicketTest {
    @Test
    fun `조조에 해당하지만 무비데이면 무비데이 할인이 선적용된다`() {
        val policies = listOf(
            MovieDayPolicy(),
            MorningPolicy()
        )
        val actual = TicketingInfo(
            policies, LocalDate.of(2023, 4, 30), LocalTime.of(9, 0),
            Price()
        ).price
        val expected = Price(9700)
        assertEquals(actual, expected)
    }

    @Test
    fun `야간에 해당하지만 무비데이면 무비데이 할인이 선적용된다`() {
        val policies = listOf(
            MovieDayPolicy(),
            NightPolicy()
        )
        val actual = TicketingInfo(
            policies, LocalDate.of(2023, 4, 30), LocalTime.of(21, 0),
            Price()
        ).price
        val expected = Price(9700)
        assertEquals(actual, expected)
    }

    private fun TicketingInfo(policies: List<DiscountPolicy>, playingDate: LocalDate, playingTime: LocalTime, price: Price): Ticket =
        Ticket.of(policies, "해리포터와 마법사의 돌", playingDate, playingTime, 1, price)
}
