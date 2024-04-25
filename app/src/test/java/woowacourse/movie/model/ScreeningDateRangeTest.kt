package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScreeningDateRangeTest {
    @Test
    fun `상영 시작 날짜부터 상영 마지막 날짜까지 하루 간격으로 상영된다`() {
        // given
        val startScreeningDate = ScreeningDate.of(2024, 4, 1)
        val endScreeningDate = ScreeningDate.of(2024, 4, 4)
        val screeningDateRange = startScreeningDate..endScreeningDate

        // when
        val actual = screeningDateRange.toList()

        // then
        assertThat(actual[0]).isEqualTo(ScreeningDate.of(2024, 4, 1))
        assertThat(actual[1]).isEqualTo(ScreeningDate.of(2024, 4, 2))
        assertThat(actual[2]).isEqualTo(ScreeningDate.of(2024, 4, 3))
        assertThat(actual[3]).isEqualTo(ScreeningDate.of(2024, 4, 4))
    }
}
