package woowacourse.movie.domain.model.seat

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class SeatColumnTest {
    @Test
    @Parameters("1", "2", "3", "4")
    internal fun `좌석은 1열부터 4열까지이다`(column: Int) {
        SeatColumn(column)
    }

    @Test
    @Parameters("0", "5")
    internal fun `좌석은 1열과 4열 사이를 벗어나면 예외가 발생한다`(column: Int) {
        assertThrows(IllegalArgumentException::class.java) {
            SeatColumn(column)
        }
    }
}
