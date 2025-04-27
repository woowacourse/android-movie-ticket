package woowacourse.movie.model.ticket

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.movie.model.ticket.seat.SeatRow

class SeatRowTest {
    @ParameterizedTest
    @CsvSource(
        "0, A",
        "3, D",
        "25, Z",
    )
    fun `보유한 값을 알파벳 문자열로 변환할 수 있다`(
        index: Int,
        expectedText: String,
    ) {
        val actualRowText = SeatRow(index).rowSeatText
        assertThat(expectedText).isEqualTo(actualRowText)
    }

    @ParameterizedTest
    @ValueSource(
        ints = [-1, 26, 99],
    )
    fun `알파벳 범위를 넘어가는 값을 가질 수 없다`(index: Int) {
        assertThrows<IllegalArgumentException> { SeatRow(index) }
    }
}
