package domain

import domain.seatPolicy.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTest {
    @Test
    internal fun `좌석이 1행 5열 이면 a,b,s 좌석 정책을 적용하면, 좌석 랭크는 B이다`() {
        // given
        val seat = Seat(1, 5, SeatPolicies())
        // when
        val actual = seat.rank
        // then
        val expected = SeatRank.B
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `좌석이 3행 5열 이고, a,b,s 좌석 정책을 적용하면, 좌석 랭크는 S이다`() {
        // given
        val seat = Seat(3, 5, SeatPolicies())
        // when
        val actual = seat.rank
        // then
        val expected = SeatRank.S
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `좌석이 5행 5열 이고, a,b,s 좌석 정책을 적용하면, 좌석 랭크는 A이다`() {
        // given
        val seat = Seat(5, 5, SeatPolicies())
        // when
        val actual = seat.rank
        // then
        val expected = SeatRank.A
        assertThat(actual).isEqualTo(expected)
    }
}
