package woowacourse.movie.fixture

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.domain.Title
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

object MovieFixture {
    val movie = Movie(
        Title("해리포터와 마법사의 돌"),
        R.drawable.movie_poster,
        ScreeningPeriod(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 25),
        ),
        152,
    )

    val dates = listOf(
        LocalDate.of(2025, 4, 1),
        LocalDate.of(2025, 4, 2),
        LocalDate.of(2025, 4, 3),
        LocalDate.of(2025, 4, 4),
        LocalDate.of(2025, 4, 5)
    )

    val dates2 = listOf(
        LocalDate.of(2025, 4, 3),
        LocalDate.of(2025, 4, 4),
        LocalDate.of(2025, 4, 5)
    )

    val dates3 = listOf(
        LocalDate.of(2025, 4, 5)
    )
}
