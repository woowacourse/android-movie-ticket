package woowacourse.movie.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SeatRowTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 6])
    fun `좌석 행의 값이 1에서 5 사이가 아닐 경우 에러를 반환한다`(rowValue: Int) {
        // given:
        // when:
        // then:
        assertThrows<IllegalArgumentException> { SeatRow(rowValue) }
    }
}
