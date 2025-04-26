package woowacourse.movie.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SeatColumnTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 5])
    fun `좌석 열의 값이 1에서 4 사이가 아닐 경우 에러를 반환한다`(columnValue: Int) {
        // given:
        // when:
        // then:
        assertThrows<IllegalArgumentException> { SeatColumn(columnValue) }
    }
}
