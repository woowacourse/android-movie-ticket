package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DateTypeTest {
    @Test
    fun `월요일은 WEEKDAY로 반환된다`() {
        // given & when
        val date = LocalDate.of(2025, 4, 7)

        // then
        assertThat(DateType.from(date)).isEqualTo(DateType.WEEKDAY)
    }

    @Test
    fun `토요일은 WEEKEND로 반환된다`() {
        // given & when
        val date = LocalDate.of(2025, 4, 5)

        // then
        assertThat(DateType.from(date)).isEqualTo(DateType.WEEKEND)
    }

    @Test
    fun `일요일은 WEEKEND로 반환된다`() {
        // given & when
        val date = LocalDate.of(2025, 4, 6)

        // then
        assertThat(DateType.from(date)).isEqualTo(DateType.WEEKEND)
    }
}
