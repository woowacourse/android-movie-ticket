package woowacourse.movie

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.DayType
import java.time.LocalDate

class DayTypeTest {
    @Test
    fun `전달받은 날짜가 평일이면 WEEKDAY를 반한한다`() {
        val type = DayType.of(LocalDate.of(2025, 4, 16))
        assertEquals(type, DayType.WEEKDAY)
    }

    @Test
    fun `전달받은 날짜가 주말이면 WEEKEND를 반한한다`() {
        val type = DayType.of(LocalDate.of(2025, 4, 19))
        assertEquals(type, DayType.WEEKEND)
    }
}
