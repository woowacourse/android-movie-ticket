package domain.seatPolicy

import domain.Seat
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RankSPolicyTest {
    @ParameterizedTest
    @ValueSource(ints = [3, 4])
    fun `좌석이 3열 또는 4열이면, true 이다`(row: Int) {
        // given
        val seat = Seat(row, 5)
        // when
        val actual = RankSPolicy().checkCondition(seat)
        // then
        Assertions.assertThat(actual).isTrue
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 5])
    fun `좌석이 1열 또는 2열이 아니라면, false 이다`(row: Int) {
        // given
        val seat = Seat(row, 5)
        // when
        val actual = RankSPolicy().checkCondition(seat)
        // then
        Assertions.assertThat(actual).isFalse
    }
}
