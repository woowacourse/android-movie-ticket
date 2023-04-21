package woowacourse.movie.domain.model.seat

import junitparams.JUnitParamsRunner
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class SeatRowTest {
    @Test
    internal fun `행이 1보다 작으면 예외가 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            SeatRow(0)
        }
    }
}
