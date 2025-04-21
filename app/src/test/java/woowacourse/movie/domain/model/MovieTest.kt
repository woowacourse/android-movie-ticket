package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate

class MovieTest {
    @Test
    fun `Movie 객체 생성 테스트`() {
        val screeningPeriod =
            ScreeningPeriod(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            )
        val runningTime = RunningTime(125)

        val movie =
            Movie(
                id = 1,
                title = "해리 포터와 마법사의 돌",
                poster = Poster.Url("https://example.com/poster.jpg"),
                screeningPeriod = screeningPeriod,
                runningTime = runningTime,
            )

        assertAll(
            { assertThat(movie.title).isEqualTo("해리 포터와 마법사의 돌") },
            { assertThat((movie.poster as? Poster.Url)?.url).isEqualTo("https://example.com/poster.jpg") },
            { assertThat(movie.screeningPeriod).isEqualTo(screeningPeriod) },
            { assertThat(movie.runningTime).isEqualTo(runningTime) },
        )
    }
}
