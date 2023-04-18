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
    fun `티켓을 생성하면 정책에 따라 계산한다`() {
        val policies = listOf(
            MovieDayPolicy(),
            MorningPolicy(),
            NightPolicy()
        )
        val actual = Ticket(
            policies, LocalDate.of(2023, 4, 30), LocalTime.of(9, 0),
            Price()
        ).price
        val expected = Price(29100)
        assertEquals(actual, expected)
    }

    private fun Ticket(policies: List<DiscountPolicy>, playingDate: LocalDate, playingTime: LocalTime, price: Price): Ticket =
        Ticket.of(policies, "해리포터와 마법사의 돌", playingDate, playingTime, 3, price)
}
