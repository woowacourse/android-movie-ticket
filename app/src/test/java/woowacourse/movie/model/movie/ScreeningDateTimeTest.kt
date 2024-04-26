package woowacourse.movie.model.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ScreeningDateTimeTest {
    @Test
    fun `평일일 경우 평일 영화 시간대를 가져온다`() {
        // given
        val date = LocalDate.of(2024, 3, 28)
        val screeningDateTime = ScreeningDateTime(date)

        // when
        val actual = screeningDateTime.screeningTime()

        // then
        assertThat(actual).isEqualTo(ScreeningDateTime.WEEKDAY_TIMES)
    }

    @Test
    fun `주말일 경우 주말 영화 시간대를 가져온다`() {
        // given
        val date = LocalDate.of(2024, 3, 30)
        val screeningDateTime = ScreeningDateTime(date)

        // when
        val actual = screeningDateTime.screeningTime()

        // then
        assertThat(actual).isEqualTo(ScreeningDateTime.WEEKEND_TIMES)
    }
}
