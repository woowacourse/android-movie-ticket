package domain

import domain.discountPolicy.DisCountPolicies
import domain.discountPolicy.MovieDay
import domain.discountPolicy.OffTime
import domain.seatPolicy.RankAPolicy
import domain.seatPolicy.RankBPolicy
import domain.seatPolicy.RankSPolicy
import domain.seatPolicy.SeatPolicies
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TicketsTest {
    private val disCountPolicies = DisCountPolicies(listOf(MovieDay(), OffTime()))
    private val seatPolicies = SeatPolicies(listOf(RankAPolicy(), RankBPolicy(), RankSPolicy()))

    @Test
    internal fun `좌석이 3행 3열이고, 상영시간이 무비데이이고, 목요일 10시인 티켓이 2장 있을 때, 가격은 23_000원이다`() {
        // given
        val tickets = makeTestTickets()
        // when
        val actual = tickets.getTotalPrice(disCountPolicies, seatPolicies).value
        // then
        val expected = 23_000
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    private fun makeTestTickets(): Tickets {
        return Tickets(List(2) { makeTestTicket() })
    }

    private fun makeTestTicket(): Ticket {
        val seat = Seat(3, 3)
        val date = LocalDate.of(2023, 4, 20)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        return Ticket(dateTime, seat)
    }
}
