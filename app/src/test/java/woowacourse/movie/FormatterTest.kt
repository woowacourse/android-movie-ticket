package woowacourse.movie

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class FormatterTest {
    @Test
    fun `Date를 정해진 형태로 포맷팅한다`() {
        val actual = Formatter.dateFormat(LocalDate.of(2023, 1, 1))
        val expected = "2023.1.1"
        assertEquals(actual, expected)
    }

    @Test
    fun `Time을 정해진 형태로 포맷팅한다`() {
        val actual = Formatter.timeFormat(LocalTime.of(1, 1))
        val expected = "01:01"
        assertEquals(actual, expected)
    }

    @Test
    fun `금액울 정해진 형태로 포맷팅한다`() {
        val actual = Formatter.decimalFormat(11111)
        val expected = "11,111"
        assertEquals(actual, expected)
    }
}
