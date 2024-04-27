package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DateRangeTest {
    @Test
    fun `날짜 범위 내의 모든 날짜를 구한다`() {
        val dateRange =
            DateRange(
                start = LocalDate.of(2022, 1, 1),
                endInclusive = LocalDate.of(2022, 1, 3),
            )

        val actual = dateRange.allDates()
        val expected =
            listOf(
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 1, 2),
                LocalDate.of(2022, 1, 3),
            )

        assertThat(actual).isEqualTo(expected)
    }
}
