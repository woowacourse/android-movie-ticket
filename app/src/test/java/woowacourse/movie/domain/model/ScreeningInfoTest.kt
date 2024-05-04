package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate

class ScreeningInfoTest {
    @ParameterizedTest
    @CsvSource("2024,4,1,true", "2024,4,28,true", "2024,5,1,false", "2025,4,20,false")
    fun `해당 날짜가 상영 날짜 범위에 속하는지 알 수 있다`(
        year: Int,
        month: Int,
        day: Int,
        expected: Boolean,
    ) {
        // given
        val startDate = LocalDate.of(2024, 4, 1)
        val endDate = LocalDate.of(2024, 4, 28)
        val screeningInfo = ScreeningInfo(startDate, endDate, 100)

        // when
        val actual = screeningInfo.isInScreeningDateRange(LocalDate.of(year, month, day))

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
