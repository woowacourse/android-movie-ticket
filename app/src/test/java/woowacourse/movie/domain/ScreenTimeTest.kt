package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movie.ScreenTime
import java.time.LocalDate

class ScreenTimeTest {
    @Test
    fun `주말을 인자로 받을 경우 반환하는 timeList 는 "0900" 으로 시작한다`() {
        val screenTime = ScreenTime(SAMPLE_WEEKEND_DATE)
        assertThat(screenTime.timeList().contains("09:00")).isTrue
    }

    @Test
    fun `평일을 인자로 받을 경우 반환하는 timeList 는 "1000" 으로 시작한다`() {
        val screenTime = ScreenTime(SAMPLE_WEEKDAY_DATE)
        assertThat(screenTime.timeList().contains("10:00")).isTrue
    }

    companion object {
        private val SAMPLE_WEEKEND_DATE: LocalDate = LocalDate.of(2024, 3, 2)
        private val SAMPLE_WEEKDAY_DATE: LocalDate = LocalDate.of(2024, 3, 1)
    }
}