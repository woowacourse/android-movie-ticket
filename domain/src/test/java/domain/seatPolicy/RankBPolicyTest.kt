package domain.seatPolicy

import domain.Seat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RankBPolicyTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2])
    fun `좌석이 1열 또는 2열이면, true 이다`(row: Int) {
        // given
        val seat = Seat(row, 5)
        // when
        val actual = RankBPolicy().checkCondition(seat)
        // then
        assertThat(actual).isTrue
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 4])
    fun `좌석이 1열 또는 2열이 아니라면, false 이다`(row: Int) {
        // given
        val seat = Seat(row, 5)
        // when
        val actual = RankBPolicy().checkCondition(seat)
        // then
        assertThat(actual).isFalse
    }
}
