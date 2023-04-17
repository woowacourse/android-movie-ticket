package movie.screening

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class ScreeningTimeTest {

    @Test
    fun `주말이면 오전 9시부터 23시까지 두 시간 간격으로 상영 시간이 반환된다`() {
        // given
        val actual = listOf<LocalTime>(
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            LocalTime.of(19, 0),
            LocalTime.of(21, 0),
            LocalTime.of(23, 0),
        )

        // when
        val expected = ScreeningTime.getScreeningTime(LocalDate.of(2023, 4, 1))

        // then
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `평일이면 오전 10시부터 00시까지 두 시간 간격으로 상영 시간이 반환된다`() {
        // given
        val actual = listOf<LocalTime>(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            LocalTime.of(16, 0),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
            LocalTime.of(0, 0),
        )

        // when
        val expected = ScreeningTime.getScreeningTime(LocalDate.of(2023, 4, 11))

        // then
        assertThat(expected).isEqualTo(actual)
    }
}
