package domain.seatPolicy

import domain.Seat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatPoliciesTest {

    @Test
    fun `A,B,S 정책 좌석을 적용할 때, 좌석이 1열 이라면, 가격은 10_000원 이다`() {
        // given
        val seatPolicies = SeatPolicies(listOf(RankAPolicy(), RankBPolicy(), RankSPolicy()))
        val seat = Seat(1, 5)
        // when
        val actual = seatPolicies.getPrice(seat).value
        // then
        val expected = 10_000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `A,B,S 정책 좌석을 적용할 때, 좌석이 3행 이라면, 가격은 15_000원 이다`() {
        // given
        val seatPolicies = SeatPolicies(listOf(RankAPolicy(), RankBPolicy(), RankSPolicy()))
        val seat = Seat(3, 5)
        // when
        val actual = seatPolicies.getPrice(seat).value
        // then
        val expected = 15_000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `A,B,S 정책 좌석을 적용할 때, 좌석이 5행 이라면, 가격은 12_000원 이다`() {
        // given
        val seatPolicies = SeatPolicies(listOf(RankAPolicy(), RankBPolicy(), RankSPolicy()))
        val seat = Seat(5, 5)
        // when
        val actual = seatPolicies.getPrice(seat).value
        // then
        val expected = 12_000
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `정책이 없을 때, 좌석이 1행 이라면, 가격은 10_000원 이다`() {
        // given
        val seatPolicies = SeatPolicies(listOf())
        val seat = Seat(5, 5)
        // when
        val actual = seatPolicies.getPrice(seat).value
        // then
        val expected = 10_000
        assertThat(actual).isEqualTo(expected)
    }
}
