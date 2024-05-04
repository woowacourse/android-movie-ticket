package woowacourse.movie.domain.model.reservation.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SeatingChartTest {
    @ParameterizedTest
    @CsvSource("0,3,B_RANK", "1,2,B_RANK", "2,0,S_RANK", "3,1,S_RANK", "4,0,A_RANK")
    fun `좌석 배치도에서 위치에 따른 등급별 좌석을 알 수 있다`(
        row: Int,
        col: Int,
        expectedRank: SeatRank,
    ) {
        val seatingChart = SeatingChart()

        val actualRank = seatingChart.classifySeatByRow(row, col).rank

        assertThat(actualRank).isEqualTo(expectedRank)
    }

    @ParameterizedTest
    @CsvSource("-1,3", "5,2", "0,4", "6,7")
    fun `좌석 배치도에서 벗어난 위치를 확인하는 경우 예외를 발생시킨다`(
        row: Int,
        col: Int,
    ) {
        val seatingChart = SeatingChart()

        assertThrows<IllegalArgumentException> { seatingChart.classifySeatByRow(row, col) }
    }
}
