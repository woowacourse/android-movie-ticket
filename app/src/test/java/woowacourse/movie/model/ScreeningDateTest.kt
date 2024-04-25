package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ScreeningDateTest {
    private val weekendScreeningTimes = listOf(9, 11, 13, 15, 17, 19, 21, 23)
    private val weekDayScreeningTimes = listOf(10, 12, 14, 16, 18, 20, 22, 24)

    private fun screeningTimes(list: List<Int>) = list.map { ScreeningTime.of(it) }

    @ParameterizedTest
    @CsvSource("2024, 4, 25", "2000, 7, 28", "2010, 1, 1")
    fun `올바른 상영일인 경우 예외가 발생하지 않는다`(
        year: Int,
        month: Int,
        day: Int,
    ) {
        assertDoesNotThrow { ScreeningDate.of(year, month, day) }
    }

    @ParameterizedTest
    @CsvSource("0, 4, 25", "2000, 0, 28", "2010, 13, 1", "2010, 5, 32")
    fun `올바르지 않은 상영일인 경우 예외가 발생한다`(
        year: Int,
        month: Int,
        day: Int,
    ) {
        assertThrows<IllegalArgumentException> { ScreeningDate.of(year, month, day) }
    }

    @Test
    fun `상영일이 2024년 7월 28일인 경우 다음 상영일은 2024년 7월 29일이다`() {
        // given
        val screeningDate = ScreeningDate.of(2024, 7, 28)

        // when
        val actual = screeningDate.nextDay()

        // then
        assertThat(actual).isEqualTo(ScreeningDate.of(2024, 7, 29))
    }

    @Test
    fun `상영일이 평일인 경우, 오전 10시부터 자정까지 2시간 간격으로 상영한다`() {
        // given
        val screeningDate = ScreeningDate.of(2024, 4, 1)

        // when
        val actual = screeningDate.screeningTimes()

        // then
        assertThat(actual).isEqualTo(screeningTimes(weekDayScreeningTimes))
    }

    @Test
    fun `상영일이 주말인 경우, 오전 9시부터 자정까지 2시간 간격으로 상영한다`() {
        // given
        val screeningDate = ScreeningDate.of(2024, 4, 6)

        // when
        val actual = screeningDate.screeningTimes()

        // then
        assertThat(actual).isEqualTo(screeningTimes(weekendScreeningTimes))
    }
}
