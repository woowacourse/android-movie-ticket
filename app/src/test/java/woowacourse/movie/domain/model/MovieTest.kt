package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieTest {
    @Test
    fun `영화 정보를 생성한다`() {
        val movie =
            Movie(
                movieId = 1,
                posterName = "harrypotter_poster",
                title = "테넷",
                screeningDates =
                    listOf(
                        LocalDate.of(2024, 4, 1),
                        LocalDate.of(2024, 4, 2),
                        LocalDate.of(2024, 4, 20),
                    ),
                runningTime = 150,
                summary = "시간을 건너뛰는 SF 영화",
            )

        assertThat(movie.posterName).isEqualTo("harrypotter_poster")
        assertThat(movie.title).isEqualTo("테넷")
        assertThat(movie.screeningDates).isEqualTo(listOf(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 2)))
        assertThat(movie.runningTime).isEqualTo(150)
        assertThat(movie.summary).isEqualTo("시간을 건너뛰는 SF 영화")
    }
}
