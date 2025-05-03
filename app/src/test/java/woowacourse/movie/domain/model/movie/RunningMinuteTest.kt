package woowacourse.movie.domain.model.movie

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RunningMinuteTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, -123123])
    fun `상영시간이 양수가 아닐 경우 예외 발생`(time: Int) {
        assertThatThrownBy { RunningMinute(time) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("상영시간은 양수여야 합니다")
    }
}
