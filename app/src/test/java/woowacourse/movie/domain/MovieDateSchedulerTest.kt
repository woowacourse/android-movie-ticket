package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieDateSchedulerTest {
    private val dateScheduler = MovieDateScheduler()

    @Test
    fun `현재 날짜가 상영 시작일보다 빠른 경우 상영 시작일 기준으로 상영 가능 날짜를 반환한다`() {
        // given
        val screeningDate = ScreeningDate(MAY_FIRST, MAY_THIRD)
        val currentDate = APRIL_THIRTIETH
        val expected: List<LocalDate> = listOf(MAY_FIRST, MAY_SECOND, MAY_THIRD)

        // when
        val actual = dateScheduler.reservableDates(screeningDate, currentDate)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `현재 날짜가 상영 시작일보다 느린 경우 현재 날짜 기준으로 상영 가능 날짜를 반환한다`() {
        // given
        val screeningDate = ScreeningDate(APRIL_THIRTIETH, MAY_THIRD)
        val currentDate = MAY_FIRST
        val expected: List<LocalDate> = listOf(MAY_FIRST, MAY_SECOND, MAY_THIRD)

        // when
        val actual = dateScheduler.reservableDates(screeningDate, currentDate)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
