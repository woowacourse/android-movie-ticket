package woowacourse.movie.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DayTypeTest {
    @Test
    fun `전달받은 날짜가 평일이면 WEEKDAY이다`() {
        val actual = DayType.of(LocalDate.of(2025, 4, 16))

        val expected = DayType.WEEKDAY

        assertEquals(actual, expected)
    }

    @Test
    fun `전달받은 날짜가 주말이면 WEEKEND이다`() {
        val actual = DayType.of(LocalDate.of(2025, 4, 19))

        val expected = DayType.WEEKEND

        assertEquals(actual, expected)
    }
}
