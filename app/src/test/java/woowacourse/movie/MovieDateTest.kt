package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.MovieDate
import java.time.LocalDate

class MovieDateTest {
    @Test
    fun `영화 상영 시작일이 오늘 이후 날짜이면 상영 시작일부터 상영 종료일 리스트를 반환한다`() {
        // given
        val movieDate =
            MovieDate(
                LocalDate.of(2024, 4, 18),
                LocalDate.of(2024, 4, 20),
            )
        val today = LocalDate.of(2024, 4, 17)

        // when
        val timeTable: List<LocalDate> = movieDate.getDateTable(today)

        // then
        assertThat(timeTable).isEqualTo(
            listOf(
                LocalDate.of(2024, 4, 18),
                LocalDate.of(2024, 4, 19),
                LocalDate.of(2024, 4, 20),
            ),
        )
    }

    @Test
    fun `영화 상영 시작일이 오늘 이전 날짜이면 오늘부터 상영 종료일 리스트를 반환한다`() {
        // given
        val movieDate =
            MovieDate(
                LocalDate.of(2024, 4, 15),
                LocalDate.of(2024, 4, 20),
            )
        val today = LocalDate.of(2024, 4, 17)

        // when
        val timeTable: List<LocalDate> = movieDate.getDateTable(today)

        // then
        assertThat(timeTable).isEqualTo(
            listOf(
                LocalDate.of(2024, 4, 17),
                LocalDate.of(2024, 4, 18),
                LocalDate.of(2024, 4, 19),
                LocalDate.of(2024, 4, 20),
            ),
        )
    }
}
