package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningPeriod
import java.time.LocalDate

class ScreeningDateTest {
    @Test
    fun `상영 기간 범위내의 날짜들을 구할 수 있다`() {
        val start = ScreeningDate(LocalDate.of(1111, 1, 1))
        val end = ScreeningDate(LocalDate.of(1111, 1, 10))
        val expected =
            (1..10).map {
                val date = LocalDate.of(1111, 1, it)
                ScreeningDate(date)
            }
        val actual = ScreeningPeriod(start, end).dates
        assertThat(actual).isEqualTo(expected)
    }
}
