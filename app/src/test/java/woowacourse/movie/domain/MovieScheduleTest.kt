package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieScheduleTest {
    @Test
    fun `영화 스케줄은 현재 상영가능한 날짜 리스트를 반환한다`() {
        // given
        val startDate = LocalDate.of(2025, 4, 13)
        val endDate = LocalDate.of(2025, 4, 17)
        val movieSchedule = MovieSchedule(Date(startDate, endDate))
        // when
        val actual = movieSchedule.dateSpinner(LocalDate.of(2025, 4, 16))
        val expected =
            listOf(
                LocalDate.of(2025, 4, 16),
                LocalDate.of(2025, 4, 17),
            )
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
