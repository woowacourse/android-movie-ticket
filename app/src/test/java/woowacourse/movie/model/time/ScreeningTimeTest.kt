package woowacourse.movie.model.time

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalTime

class ScreeningTimeTest {
    @ParameterizedTest
    @CsvSource("12, 0", "4, 41", "0, 0")
    fun `올바른 상영시간인 경우 예외가 발생하지 않는다`(
        hour: Int,
        minute: Int,
    ) {
        assertDoesNotThrow { ScreeningTime.of(hour, minute) }
    }

    @ParameterizedTest
    @CsvSource("-1, 10", "25, 0", "3, 60")
    fun `올바르지 않은 상영시간인 경우 예외가 발생한다`(
        hour: Int,
        minute: Int,
    ) {
        assertThrows<IllegalArgumentException> { ScreeningTime.of(hour, minute) }
    }

    @Test
    fun `시간이 24시인 경우 0시로 저장한다`() {
        val screeningTime = ScreeningTime.of(24, 0)
        assertThat(screeningTime).isEqualTo(ScreeningTime(LocalTime.of(0, 0)))
    }

    @Test
    fun `분의 기본값은 0분이다`() {
        val screeningTime = ScreeningTime.of(5)
        assertThat(screeningTime).isEqualTo(ScreeningTime(LocalTime.of(5, 0)))
    }
}
