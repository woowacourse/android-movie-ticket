package woowacourse.movie.domain.model.seat

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class SeatRowTest {
    @Test
    @Parameters("1", "2", "3", "4", "5")
    internal fun `좌석은 1행부터 5행까지이다`(row: Int) {
        SeatRow(row)
    }

    @Test
    internal fun `행이 1보다 작으면 예외가 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            SeatRow(0)
        }
    }
}
