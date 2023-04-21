package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.seat.Grade
import woowacourse.movie.domain.seat.SeatSelectSystem
import woowacourse.movie.domain.seat.SelectResult
import woowacourse.movie.domain.theater.TheaterInfo

class SeatSelectSystemTest {
    private lateinit var system: SeatSelectSystem

    @Before
    fun setUp() {
        val rowGrade = mapOf(
            0 to Grade.B,
            1 to Grade.B,
            2 to Grade.S,
            3 to Grade.S,
            4 to Grade.A,
        )
        val row = 5
        val col = 4
        val theaterInfo = TheaterInfo(rowGrade, row, col)
        system = SeatSelectSystem(theaterInfo, 3)
    }

    @Test
    fun `선택할 수 있는 자리라면 Selection을 반환한다`() {
        val actual = system.select(0, 0)
        assertTrue(actual is SelectResult.Selection)
    }

    @Test
    fun `선택 후 선택한 자리의 가격을 함께 반환한다`() {
        val actual = system.select(0, 0)
        if (actual is SelectResult.Selection) {
            assertEquals(actual.seatPrice, Grade.B.price)
        }
        assertTrue(actual is SelectResult.Selection)
    }

    @Test
    fun `존재하지 않는 자리라면 WrongInput을 반환한다`() {
        val actual = system.select(1, 100)
        assertTrue(actual is SelectResult.WrongInput)
    }

    @Test
    fun `가격 정보가 없는 자리라면 WrongInput을 반환한다`() {
        val actual = system.select(6, 1)
        assertTrue(actual is SelectResult.WrongInput)
    }

    @Test
    fun `이미 선택한 자리라면 Deselection을 반환한다`() {
        system.select(0, 0)
        val actual = system.select(0, 0)
        assertTrue(actual is SelectResult.Deselection)
    }
}
