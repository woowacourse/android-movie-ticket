package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScreeningDatesTest {
    @Test
    fun `영화_상영일의_시작날짜는_종료날짜보다_이르거나_같아야한다`() {
        val startDate = LocalDate("2024.04.01")
        val endDate = LocalDate("2024.04.01")
        assertDoesNotThrow { ScreeningDates(startDate, endDate) }
    }

    @Test
    fun `영화_상영일의_시작날짜는_종료날짜보다_늦을_수_없다`() {
        val startDate = LocalDate("2024.04.02")
        val endDate = LocalDate("2024.04.01")
        assertThrows<IllegalArgumentException> { ScreeningDates(startDate, endDate) }
    }

    @Test
    fun `영화_상영일은_시작날짜부터_종료날짜까지의_날짜_범위_리스트를_갖는다`() {
        val startDate = LocalDate("2024.04.01")
        val endDate = LocalDate("2024.04.03")

        val expected =
            listOf(LocalDate("2024.04.01"), LocalDate("2024.04.02"), LocalDate("2024.04.03"))
        val actual = ScreeningDates(startDate, endDate).getDatesBetweenStartAndEnd()
        assertThat(actual).isEqualTo(expected)
    }

    private fun LocalDate(dateString: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return LocalDate.parse(dateString, formatter)
    }
}
