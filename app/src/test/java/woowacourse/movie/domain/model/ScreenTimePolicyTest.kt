package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class ScreenTimePolicyTest {
    @Test
    fun `screening time of weekend is from 10H to 22H step 2H`() {
        val screenTimePolicy = WeeklyScreenTimePolicy() // saturday
        val actual = screenTimePolicy.screeningTimes(LocalDate.of(2024, 4, 27))

        val expected =
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
            )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `screeningTime of weekday is from 09 to 23 step 2H`() {
        val screenTimePolicy = WeeklyScreenTimePolicy() // friday
        val actual = screenTimePolicy.screeningTimes(LocalDate.of(2024, 4, 26))

        val expected =
            listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )

        assertThat(actual).isEqualTo(expected)
    }
}
