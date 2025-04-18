package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate

class DayTypeTest {
    @ParameterizedTest
    @ValueSource(ints = [14, 15, 16, 17, 18])
    fun `주어진 날짜가 주중인지 확인할 수 있다`(value: Int) {
        // given
        val date: LocalDate = LocalDate.of(2025, 4, value)

        // when
        val actual: DayType = DayType.of(date)
        val expected: DayType = DayType.WEEKDAY

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [19, 20])
    fun `주어진 날짜가 주말인지 확인할 수 있다`(value: Int) {
        // given
        val date: LocalDate = LocalDate.of(2025, 4, value)

        // when
        val actual: DayType = DayType.of(date)
        val expected: DayType = DayType.WEEKEND

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
