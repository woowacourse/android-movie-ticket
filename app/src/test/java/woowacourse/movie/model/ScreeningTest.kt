package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.screening.Screening
import java.time.LocalDate

class ScreeningTest {
    private lateinit var screening: Screening

    @BeforeEach
    fun initial() {
        // given
        val movie = Movie("테스트 영화 제목", 123, "test_movie_id")
        val startDate = LocalDate.of(2025, 4, 1)
        val endDate = LocalDate.of(2025, 4, 30)

        // when
        screening = Screening(movie, startDate..endDate)
    }

    @Test
    fun `Screening 객체는 Movie 정보를 포함한다`() {
        // then
        assertThat(screening.movieId).isEqualTo("test_movie_id")
        assertThat(screening.title).isEqualTo("테스트 영화 제목")
        assertThat(screening.runningTime).isEqualTo(123)
        assertThat(screening.period.start).isEqualTo(LocalDate.of(2025, 4, 1))
        assertThat(screening.period.endInclusive).isEqualTo(LocalDate.of(2025, 4, 30))
    }

    @Test
    fun `Screening 객체는 상영 기간의 모든 날짜를 반환한다`() {
        // when
        val dates: List<LocalDate> = screening.getScreeningDates()

        // then
        assertThat(dates).hasSize(30)
        assertThat(dates[0]).isEqualTo(LocalDate.of(2025, 4, 1))
        assertThat(dates[4]).isEqualTo(LocalDate.of(2025, 4, 5))
    }
}
