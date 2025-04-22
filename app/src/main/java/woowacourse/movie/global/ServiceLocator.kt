package woowacourse.movie.global

import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

object ServiceLocator {
    var movies: List<Movie> =
        listOf<Movie>(
            Movie(
                "해리포터와 마법사의 돌",
                "images/movie_poster.jpg",
                LocalDateTime.of(2025, 4, 1, 0, 0, 0),
                LocalDateTime.of(2025, 4, 25, 23, 59, 59),
                125.minutes,
            ),
        )
    var today: LocalDate = LocalDate.now()
    var now: LocalDateTime = LocalDateTime.now()
}
