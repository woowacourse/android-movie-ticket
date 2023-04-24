package woowacourse.movie.domain.model.rules

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.rules.ScreeningTimes
import java.time.LocalDate
import java.time.LocalTime

class ScreeningTimesTest {
    @Test
    fun `평일에는 오전 10시부터 두 시간 간격으로 상영한다`() {
        // given
        val weekDay = LocalDate.of(2023, 4, 12)
        val expected = (10..23 step 2).map { LocalTime.of(it, 0) }

        // when
        val actual = ScreeningTimes.getScreeningTime(weekDay)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `주말에는 오전 9시부터 두 시간 간격으로 상영한다`() {
        // given
        val weekDay = LocalDate.of(2023, 4, 15)
        val expected = (9..23 step 2).map { LocalTime.of(it, 0) }

        // when
        val actual = ScreeningTimes.getScreeningTime(weekDay)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
