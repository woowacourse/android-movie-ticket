package woowacourse.movie

import com.woowacourse.domain.seat.SeatColumn
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SeatColumnTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2])
    fun `column의 값은 0 이상이어야 한다`(value: Int) {
        val actual = SeatColumn(value).value
        Assertions.assertThat(actual).isEqualTo(value)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3])
    fun `column의 값이 음수이면 예외처리한다`(value: Int) {
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { SeatColumn(value).value }
            .withMessageContaining("잘못된 값: $value 0 이상의 값을 입력해주세요.")
    }
}
