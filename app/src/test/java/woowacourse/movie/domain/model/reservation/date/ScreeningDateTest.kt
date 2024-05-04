package woowacourse.movie.domain.model.reservation.date

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate

class ScreeningDateTest {
    @ParameterizedTest
    @CsvSource("4,20,true", "4,24,false", "6,2,true", "12,24,false")
    fun `해당 날짜가 주말인지 평일인지 알 수 있다`(
        month: Int,
        day: Int,
        isWeekend: Boolean,
    ) {
        // given
        val screeningDate = ScreeningDate(LocalDate.of(2024, month, day), 100)

        // when
        val actual = screeningDate.isWeekend()

        // then
        assertThat(actual).isEqualTo(isWeekend)
    }

    @ParameterizedTest
    @CsvSource("2024,4,24", "2024,6,20", "2025,4,20")
    fun `날짜를 변경할 수 있다`(
        year: Int,
        month: Int,
        day: Int,
    ) {
        // given
        val initialYear = 2024
        val initialMonth = 4
        val initialDay = 20
        val screeningDate =
            ScreeningDate(LocalDate.of(initialYear, initialMonth, initialDay), 100)

        // when
        screeningDate.changeDate(year, month, day)

        // then
        assertThat(LocalDate.of(initialYear, initialMonth, initialDay)).isNotEqualTo(screeningDate)
    }
}
