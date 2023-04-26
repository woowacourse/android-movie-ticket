package domain

import domain.discountPolicy.DisCountPolicies
import domain.seatPolicy.SeatPolicies
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TicketsTest {
    @Test
    internal fun `좌석이 3행 3열이고, 상영시간이 무비데이이고, 목요일 10시인 티켓이 2장 있을 때, 가격은 23_000원이다`() {
        // given
        val tickets = Tickets(listOf(makeTestTicket()))
        tickets.addTicket(makeTestTicket())
        // when
        val actual = tickets.price.value
        // then
        val expected = 23_000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `같은 티켓을 포함하고 있으면 true를 반환한다`() {
        // given
        val tickets = Tickets(listOf(makeTestTicket()))
        // when
        val actual = tickets.isContainSameTicket(makeTestTicket())
        // then
        assertThat(actual).isTrue
    }

    private fun makeTestTicket(): Ticket {
        val date = LocalDate.of(2023, 4, 20)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        return Ticket(dateTime, Seat(3, 3), DisCountPolicies())
    }
}
