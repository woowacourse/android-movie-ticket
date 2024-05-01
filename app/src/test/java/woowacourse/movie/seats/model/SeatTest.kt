package woowacourse.movie.seats.model

import android.graphics.Color
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SeatTest {
    @Test
    fun `좌석이 선택되면 배경색이 노란색으로 바뀌어야 한다`() {
        // given
        val seat = Seat.of(1, 1)
        // when
        seat.select()
        // then
        assertThat(seat.cellBackgroundColor).isEqualTo(Color.YELLOW)
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1, B",
        "1, 1, B",
        "2, 1, S",
        "3, 1, S",
        "4, 1, A",
    )
    fun `좌석의 열에 따라 등급이 결정되어야 한다`(
        row: Int,
        col: Int,
        rank: SeatRank,
    ) {
        // given, when
        val seat = Seat.of(row, col)
        // then
        assertThat(seat.rank).isEqualTo(rank)
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1, A2",
        "1, 2, B3",
        "2, 3, C4",
        "3, 2, D3",
        "4, 1, E2",
    )
    fun `좌석의 열과 행에 따라 좌석번호가 정해져야 한다`(
        row: Int,
        col: Int,
        coordinate: String,
    ) {
        // given, when
        val seat = Seat.of(row, col)
        // then
        assertThat(seat.coordinate).isEqualTo(coordinate)
    }

    @Test
    fun `좌석끼리의 동등성 비교가 가능해야 한다`() {
        // given
        val seat1 = Seat.of(0, 0)
        val seat2 = Seat.of(0, 0)
        // then
        Assertions.assertTrue(seat1 == seat2)
    }
}
