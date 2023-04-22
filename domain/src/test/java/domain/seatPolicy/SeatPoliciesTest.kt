package domain.seatPolicy

import domain.Seat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatPoliciesTest {

    @Test
    fun `A,B,S 정책 좌석을 적용할 때, 좌석이 1열 이라면, 좌석 랭크는 B이다`() {
        // given
        val seatPolicies = SeatPolicies(listOf(RankAPolicy(), RankBPolicy(), RankSPolicy()))
        val seat = Seat(1, 5,SeatPolicies())
        // when
        val actual = seatPolicies.getSeatRank(seat)
        // then
        val expected = SeatRank.B
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `A,B,S 정책 좌석을 적용할 때, 좌석이 3행 이라면, 좌석 랭크는 S 이다`() {
        // given
        val seatPolicies = SeatPolicies(listOf(RankAPolicy(), RankBPolicy(), RankSPolicy()))
        val seat = Seat(3, 5,SeatPolicies())
        // when
        val actual = seatPolicies.getSeatRank(seat)
        // then
        val expected = SeatRank.S
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `A,B,S 정책 좌석을 적용할 때, 좌석이 5행 이라면, 좌석 랭크는 A 이다`() {
        // given
        val seatPolicies = SeatPolicies(listOf(RankAPolicy(), RankBPolicy(), RankSPolicy()))
        val seat = Seat(5, 5,SeatPolicies())
        // when
        val actual = seatPolicies.getSeatRank(seat)
        // then
        val expected = SeatRank.A
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `정책이 없을 때, 좌석이 1행 이라면, 좌석 랭크는 B 이다`() {
        // given
        val seatPolicies = SeatPolicies(listOf())
        val seat = Seat(5, 5,SeatPolicies())
        // when
        val actual = seatPolicies.getSeatRank(seat)
        // then
        val expected = SeatRank.B
        assertThat(actual).isEqualTo(expected)
    }
}
