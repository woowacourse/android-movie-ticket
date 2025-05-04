package woowacourse.movie.model.ticket

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.movie.model.ticket.seat.SeatRow

class SeatRowTest {
    @ParameterizedTest
    @ValueSource(
        ints = [-1, 26, 99],
    )
    fun `알파벳 범위를 넘어가는 값을 가질 수 없다`(index: Int) {
        assertThrows<IllegalArgumentException> { SeatRow(index) }
    }
}
