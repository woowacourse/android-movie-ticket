package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ScreeningTimeTest {
    @Test
    fun `주말이면 9 ~ 23을 반환`() {
        val weekend = LocalDate.of(2024, 4, 7)
        val screeningTime = ScreeningTime(weekend)

        val screeningTimeSchedule = screeningTime.schedule()

        assertThat(screeningTimeSchedule).isEqualTo(9..23 step 2)
    }

    @Test
    fun `평일이면 10 ~ 24를 반환`() {
        val weekday = LocalDate.of(2024, 4, 8)
        val screeningTime = ScreeningTime(weekday)

        val screeningTimeSchedule = screeningTime.schedule()

        assertThat(screeningTimeSchedule).isEqualTo(10..24 step 2)
    }
}
