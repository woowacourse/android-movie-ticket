package domain

import domain.discountPolicy.DisCountPolicies
import domain.discountPolicy.MovieDay
import domain.discountPolicy.OffTime
import domain.seatPolicy.RankAPolicy
import domain.seatPolicy.RankBPolicy
import domain.seatPolicy.RankSPolicy
import domain.seatPolicy.SeatPolicies
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TicketTest {
    private val disCountPolicies = DisCountPolicies(listOf(MovieDay(), OffTime()))
    private val seatPolicies = SeatPolicies(listOf(RankAPolicy(), RankBPolicy(), RankSPolicy()))

    @Test
    internal fun `좌석이 1행 3열이고, 상영시간이 무비데이가 아닌 토요일 9시일 때, 가격은 7000원이다`() {
        // given
        val seat = Seat(1, 3,seatPolicies)
        val date = LocalDate.of(2023, 4, 22)
        val time = LocalTime.of(9, 0)
        val dateTime = LocalDateTime.of(date, time)
        val ticket = Ticket(dateTime, seat, disCountPolicies)
        // when
        val actual = ticket.discountPrice.value
        // then
        val expected = 8000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `좌석이 3행 3열이고, 상영시간이 무비데이가 아닌 토요일 9시일 때, 가격은 13_000원이다`() {
        // given
        val seat = Seat(3, 3,seatPolicies)
        val date = LocalDate.of(2023, 4, 22)
        val time = LocalTime.of(9, 0)
        val dateTime = LocalDateTime.of(date, time)
        val ticket = Ticket(dateTime, seat,disCountPolicies)
        // when
        val actual = ticket.discountPrice.value
        // then
        val expected = 13_000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `좌석이 3행 3열이고, 상영시간이 무비데이인 목요일 10시일 때, 가격은 11_500원이다`() {
        // given
        val seat = Seat(3, 3,seatPolicies)
        val date = LocalDate.of(2023, 4, 20)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        val ticket = Ticket(dateTime, seat, disCountPolicies)
        // when
        val actual = ticket.discountPrice.value
        // then
        val expected = 11_500
        assertThat(actual).isEqualTo(expected)
    }
}
