package woowacourse.movie

import com.woowacourse.domain.seat.Seat
import com.woowacourse.domain.seat.SeatColumn
import com.woowacourse.domain.seat.SeatRow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SeatTest {
    @Test
    fun `좌석은 행을 가지고 있다`() {
        val actual = Seat(SeatRow(1), SeatColumn(2)).row.value
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `좌석은 열을 가지고 있다`() {
        val actual = Seat(SeatRow(1), SeatColumn(2)).column.value
        assertThat(actual).isEqualTo(2)
    }

    @ParameterizedTest
    @CsvSource("2, 1, -1", "1, 2, -1", "2, 2, -1")
    fun `다른 좌석보다 행과 열값이 작으면 작은 값이다`(row: Int, column: Int, result: Int) {
        val seat = Seat(SeatRow(1), SeatColumn(1))
        val bigSeat = Seat(SeatRow(row), SeatColumn(column))

        assertThat(seat.compareTo(bigSeat)).isEqualTo(result)
    }
}
