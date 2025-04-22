package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate

class MovieTest {
    @Test
    fun `Movie_객체_생성_테스트`() {
        val movie =
            Movie(
                title = "해리 포터와 마법사의 돌",
                screeningPeriod =
                    ScreeningPeriod(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 25),
                    ),
                runningTime = RunningTime(152),
                poster = "https://example/movie.png",
            )

        assertAll(
            { assertThat(movie.title).isEqualTo("해리 포터와 마법사의 돌") },
            {
                assertThat(movie.screeningPeriod).isEqualTo(
                    ScreeningPeriod(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 25),
                    ),
                )
            },
            { assertThat(movie.runningTime).isEqualTo(RunningTime(152)) },
            { assertThat(movie.poster).isEqualTo("https://example/movie.png") },
        )
    }
}
