package movie.screening

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ScreeningDateTest {
    @Test
    fun `2023년 4월 1일부터 2023년 4월 10일까지의 날짜를 반환한다`() {
        // given
        val screeningDate = ScreeningDate(
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 10),
        )

        val actual = (1..10).map {
            LocalDate.of(2023, 4, it).toString()
        }

        // when
        val expected = screeningDate.getScreeningDate()

        // then
        assertThat(expected).isEqualTo(actual)
    }
}
