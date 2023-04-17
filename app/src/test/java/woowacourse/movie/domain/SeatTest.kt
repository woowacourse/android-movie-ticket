package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test

class SeatTest {
    @Test
    fun `1열에 대해 좌석 인덱스 번호가 주어졌을 때, 열의 문자와 행의 숫자를 이름으로 반환한다`() {
        val actual = Seat(0).getSeatName()
        assertEquals(actual, "A1")
    }

    @Test
    fun `2열에 대해 좌석 인덱스 번호가 주어졌을 때, 열의 문자와 행의 숫자를 이름으로 반환한다`() {
        val actual = Seat(5).getSeatName()
        assertEquals(actual, "B2")
    }

    @Test
    fun `3열에 대해 좌석 인덱스 번호가 주어졌을 때, 열의 문자와 행의 숫자를 이름으로 반환한다`() {
        val actual = Seat(10).getSeatName()
        assertEquals(actual, "C3")
    }

    @Test
    fun `4열에 대해 좌석 인덱스 번호가 주어졌을 때, 열의 문자와 행의 숫자를 이름으로 반환한다`() {
        val actual = Seat(12).getSeatName()
        assertEquals(actual, "D1")
    }

    @Test
    fun `5열에 대해 좌석 인덱스 번호가 주어졌을 때, 열의 문자와 행의 숫자를 이름으로 반환한다`() {
        val actual = Seat(17).getSeatName()
        assertEquals(actual, "E2")
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 C열이면 S등급을 반환한다`() {
        val actual = Seat(10).getSeatGrade()
        assertEquals(actual, SeatGrade.S_CLASS)
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 D열이면 S등급을 반환한다`() {
        val actual = Seat(12).getSeatGrade()
        assertEquals(actual, SeatGrade.S_CLASS)
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 A열이면 B등급을 반환한다`() {
        val actual = Seat(0).getSeatGrade()
        assertEquals(actual, SeatGrade.B_CLASS)
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 B열이면 B등급을 반환한다`() {
        val actual = Seat(5).getSeatGrade()
        assertEquals(actual, SeatGrade.B_CLASS)
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 E열이면 A등급을 반환한다`() {
        val actual = Seat(17).getSeatGrade()
        assertEquals(actual, SeatGrade.A_CLASS)
    }
}
