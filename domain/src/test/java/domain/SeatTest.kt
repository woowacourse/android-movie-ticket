package domain

import domain.seatPolicy.RankAPolicy
import domain.seatPolicy.RankBPolicy
import domain.seatPolicy.RankSPolicy
import domain.seatPolicy.SeatPolicies
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTest {
    @Test
    internal fun `좌석이 1행 5열 이고, a,b,s 좌석 정책을 적용하면, 가격은 10_000원이다`() {
        // given
        val seat = Seat(1, 5)
        val seatPolicies = SeatPolicies(listOf(RankAPolicy(), RankBPolicy(), RankSPolicy()))
        // when
        val actual = seat.getPrice(seatPolicies).value
        // then
        val expected = 10_000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `좌석이 3행 5열 이고, a,b,s 좌석 정책을 적용하면, 가격은 15_000원이다`() {
        // given
        val seat = Seat(3, 5)
        val seatPolicies = SeatPolicies(listOf(RankSPolicy(), RankAPolicy(), RankBPolicy()))
        // when
        val actual = seat.getPrice(seatPolicies).value
        // then
        val expected = 15_000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `좌석이 5행 5열 이고, a,b,s 좌석 정책을 적용하면, 가격은 12_000원이다`() {
        // given
        val seat = Seat(5, 5)
        val seatPolicies = SeatPolicies(listOf(RankSPolicy(), RankAPolicy(), RankBPolicy()))
        // when
        val actual = seat.getPrice(seatPolicies).value
        // then
        val expected = 12_000
        assertThat(actual).isEqualTo(expected)
    }
}
