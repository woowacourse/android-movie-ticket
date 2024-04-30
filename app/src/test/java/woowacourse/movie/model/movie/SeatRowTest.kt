package woowacourse.movie.model.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatRowTest {
    @Test
    fun `행이 0번이면 따른 좌석의 행 A를 반환한다`() {
        // given
        val row = 0

        // when
        val actual = SeatRow.findRow(row)

        // then
        assertThat(actual).isEqualTo(SeatRow.A)
    }

    @Test
    fun `행이 1번이면 따른 좌석의 행 B를반환한다`() {
        // given
        val row = 1

        // when
        val actual = SeatRow.findRow(row)

        // then
        assertThat(actual).isEqualTo(SeatRow.B)
    }
}
