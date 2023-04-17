package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDate

class DayOfWeekTest {
    @Test
    fun `월요일이라면 평일을 반환한다`() {
        val date = LocalDate.of(2023, 4, 17)
        val actual = DayOfWeek.checkDayOfWeek(date)
        assertEquals(actual, DayOfWeek.WEEKDAY)
    }

    @Test
    fun `토요일이라면 주말을 반환한다`() {
        val date = LocalDate.of(2023, 4, 15)
        val actual = DayOfWeek.checkDayOfWeek(date)
        assertEquals(actual, DayOfWeek.WEEKEND)
    }
}
