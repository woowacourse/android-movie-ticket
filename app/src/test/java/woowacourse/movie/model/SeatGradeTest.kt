package woowacourse.movie.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SeatGradeTest {
    @ParameterizedTest
    @CsvSource(value = ["1,B", "2,B", "3,S", "4,S", "5,A"])
    fun `좌석 행에 따라 좌석 등급을 반환한다`(
        rowValue: Int,
        expected: SeatGrade,
    ) {
        // given:
        val row = SeatRow(rowValue)

        // when:
        val actual: SeatGrade = SeatGrade.calculateSeatGrade(row)

        // then:
        assertEquals(expected, actual)
    }
}
