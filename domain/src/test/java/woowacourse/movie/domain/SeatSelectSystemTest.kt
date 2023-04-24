package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import woowacourse.movie.domain.seat.Grade
import woowacourse.movie.domain.system.SeatSelectSystem
import woowacourse.movie.domain.system.SelectResult
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
        assertTrue(actual is SelectResult.Success.Selection)
    }

    @Test
    fun `선택 후 선택한 자리의 가격을 함께 반환한다`() {
        val actual = system.select(0, 0)
        assertTrue(actual is SelectResult.Success.Selection)
        assertEquals((actual as SelectResult.Success.Selection).seatPrice, Grade.B.price)
    }

    @Test
    fun `선택 후 모든 자리 선택을 완료했으면 참을 함께 반환한다`() {
        system.select(1, 3)
        system.select(1, 2)
        val actual = system.select(0, 0)
        assertTrue(actual is SelectResult.Success.Selection)
        assertTrue((actual as SelectResult.Success.Selection).isSelectAll)
    }

    @Test
    fun `선택 후 모든 자리 선택을 완료하기 전이라면 거짓을 함께 반환한다`() {
        system.select(1, 3)
        val actual = system.select(0, 0)
        assertTrue(actual is SelectResult.Success.Selection)
        assertFalse((actual as SelectResult.Success.Selection).isSelectAll)
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
        assertTrue(actual is SelectResult.Success.Deselection)
    }
}
