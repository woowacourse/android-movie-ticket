package woowacourse.movie.domain.model.seat

import junitparams.JUnitParamsRunner
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class SeatColumnTest {
    @Test
    internal fun `열이 1보다 작으면 예외가 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            SeatColumn(0)
        }
    }
}
