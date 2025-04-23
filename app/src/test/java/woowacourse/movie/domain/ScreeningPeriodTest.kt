package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.fixture.MovieFixture

class ScreeningPeriodTest {
    @Test
    fun `영화_개봉_날짜는_영화_종영_날짜보다_전이여야한다`() {
        val screeningStartDate = LocalDate.of(2025, 4, 1)
        val screeningEndDate = LocalDate.of(2025, 3, 29)

        assertThrows<IllegalArgumentException> {
            ScreeningPeriod(
                screeningStartDate,
                screeningEndDate
            )
        }
    }

    @Test
    fun `영화가_개봉되었는지_알_수_있다1`() {
        val screeningPeriod = ScreeningPeriod.ofDot("2025.04.01", "2025.04.25")
        val targetDate = LocalDate.of(2025, 4, 1)

        val actual = screeningPeriod.isStart(targetDate)

        assertThat(actual).isTrue
    }

    @Test
    fun `영화가_개봉되었는지_알_수_있다2`() {
        val screeningPeriod = ScreeningPeriod.ofDot("2025.04.01", "2025.04.25")
        val targetDate = LocalDate.of(2025, 3, 31)

        val actual = screeningPeriod.isStart(targetDate)

        assertThat(actual).isFalse
    }

    @Test
    fun `영화가_종영되었는지_알_수_있다1`() {
        val screeningPeriod = ScreeningPeriod.ofDot("2025.04.01", "2025.04.25")
        val targetDate = LocalDate.of(2025, 4, 26)

        val actual = screeningPeriod.isEnd(targetDate)

        assertThat(actual).isTrue
    }

    @Test
    fun `영화가_종영되었는지_알_수_있다2`() {
        val screeningPeriod = ScreeningPeriod.ofDot("2025.04.01", "2025.04.25")
        val targetDate = LocalDate.of(2025, 4, 25)

        val actual = screeningPeriod.isEnd(targetDate)

        assertThat(actual).isFalse
    }

    @Test
    fun `영화_예매_가능한_날짜를_반환한다1`() {
        val screeningPeriod = ScreeningPeriod.ofDot("2025.04.01", "2025.04.05")
        val targetDate = LocalDate.of(2025, 4, 1)

        val actual = screeningPeriod.betweenDates(targetDate)

        val expected = MovieFixture.dates

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화_예매_가능한_날짜를_반환한다2`() {
        val screeningPeriod = ScreeningPeriod.ofDot("2025.04.01", "2025.04.05")
        val targetDate = LocalDate.of(2025, 3, 31)

        val actual = screeningPeriod.betweenDates(targetDate)

        val expected = MovieFixture.dates

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화_예매_가능한_날짜를_반환한다3`() {
        val screeningPeriod = ScreeningPeriod.ofDot("2025.04.01", "2025.04.05")
        val targetDate = LocalDate.of(2025, 4, 3)

        val actual = screeningPeriod.betweenDates(targetDate)

        val expected = MovieFixture.dates2

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화_예매_가능한_날짜를_반환한다4`() {
        val screeningPeriod = ScreeningPeriod.ofDot("2025.04.01", "2025.04.05")
        val targetDate = LocalDate.of(2025, 4, 5)

        val actual = screeningPeriod.betweenDates(targetDate)

        val expected = MovieFixture.dates3

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화_예매_가능한_날짜를_반환한다5`() {
        val screeningPeriod = ScreeningPeriod.ofDot("2025.04.01", "2025.04.05")
        val targetDate = LocalDate.of(2025, 4, 7)

        val actual = screeningPeriod.betweenDates(targetDate)

        val expected = listOf<LocalDate>()

        assertThat(actual).isEqualTo(expected)
    }
}
