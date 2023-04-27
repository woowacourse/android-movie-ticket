package domain.seatPolicy

import domain.Seat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RankAPolicyTest {
    @Test
    internal fun `좌석이 5열이면 true 이다`() {
        // given
        val seat = Seat(row = 5, 5,SeatPolicies())
        val rankAPolicy = RankAPolicy()
        // when
        val actual = rankAPolicy.checkCondition(seat)
        // then
        assertThat(actual).isTrue
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    internal fun `좌석이 5열이 아니면 false 이다`(row: Int) {
        // given
        val seat = Seat(row = row, 5,SeatPolicies())
        val rankAPolicy = RankAPolicy()
        // when
        val actual = rankAPolicy.checkCondition(seat)
        // then
        assertThat(actual).isFalse
    }
}
