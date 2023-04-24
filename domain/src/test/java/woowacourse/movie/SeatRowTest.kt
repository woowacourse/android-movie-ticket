package woowacourse.movie

import com.woowacourse.domain.seat.SeatRow
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SeatRowTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2])
    fun `row의 값은 0 이상이어야 한다`(value: Int) {
        val actual = SeatRow(value).value
        assertThat(actual).isEqualTo(value)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3])
    fun `row의 값이 음수이면 예외처리한다`(value: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { SeatRow(value).value }
            .withMessageContaining("잘못된 값: $value 0 이상의 값을 입력해주세요.")
    }
}
