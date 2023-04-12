package woowacourse.movie.domain.datetime

import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

class ScreeningDateTimeTest {
    @Test(expected = IllegalArgumentException::class)
    fun `영화 상영시간은 영화 상영 기간안에 존재해야한다`() {
        val screeningPeriod = ScreeningPeriod(
            LocalDate.parse("2023-02-01"),
            LocalDate.parse("2023-03-01")
        )
        ScreeningDateTime(LocalDateTime.of(2024, 1, 1, 11, 11, 11), screeningPeriod)
    }
}
