package woowacourse.movie.fixture

import woowacourse.movie.domain.Movie
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

object AndroidTestFixture {
    val movies1 =
        listOf<Movie>(
            Movie(
                "해리포터와 마법사의 돌",
                "images/movie_poster.jpg",
                LocalDateTime.of(2025, 4, 1, 0, 0, 0),
                LocalDateTime.of(2025, 4, 25, 23, 59, 59),
                125.minutes,
            ),
        )
    val movie1 =
        Movie(
            "해리포터와 마법사의 돌",
            "images/movie_poster.jpg",
            LocalDateTime.of(2025, 4, 3, 0, 0, 0),
            LocalDateTime.of(2025, 4, 5, 23, 59, 59),
            125.minutes,
        )
}
