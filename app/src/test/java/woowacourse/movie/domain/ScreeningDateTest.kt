package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import woowacourse.movie.APRIL_THIRTIETH
import woowacourse.movie.MAY_FIRST
import woowacourse.movie.MAY_SECOND
import woowacourse.movie.MAY_THIRD
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScreeningDateTest {
    @Test
    fun `상영 종료일이 상영 시작일 이후가 아닐 경우 예외 발생`() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val startDate: LocalDate = LocalDate.parse("2025.04.26", formatter)
        val endDate: LocalDate = LocalDate.parse("2025.04.25", formatter)

        assertThatThrownBy { ScreeningDate(startDate, endDate) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("상영 종료일은 상영 시작일 이후여야 합니다")
    }

    @Test
    fun `현재 날짜가 상영 시작일보다 빠른 경우 상영 시작일 기준으로 상영 가능 날짜를 반환한다`() {
        // given
        val screeningDate = ScreeningDate(MAY_FIRST, MAY_THIRD)
        val currentDate = APRIL_THIRTIETH
        val expected: List<LocalDate> = listOf(MAY_FIRST, MAY_SECOND, MAY_THIRD)

        // when
        val actual = screeningDate.reservableDates(currentDate)

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
        val actual = screeningDate.reservableDates(currentDate)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
