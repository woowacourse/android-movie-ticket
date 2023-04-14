package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class DateRangeTest {

    @Test
    fun `1월 3일부터 1월 7일 까지의 List를 생성한다`() {
        // given
        val startDate = LocalDate.of(2023, 1, 3)
        val endDate = LocalDate.of(2023, 1, 7)
        val dateRange = DateRange(startDate, endDate)

        // when
        val dates = dateRange.toList()

        // then
        val expect = listOf(
            LocalDate.of(2023, 1, 3),
            LocalDate.of(2023, 1, 4),
            LocalDate.of(2023, 1, 5),
            LocalDate.of(2023, 1, 6),
            LocalDate.of(2023, 1, 7),
        )
        assertEquals(dates, expect)
    }
}
