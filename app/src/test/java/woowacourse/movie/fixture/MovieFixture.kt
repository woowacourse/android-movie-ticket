package woowacourse.movie.fixture

import woowacourse.movie.domain.Movie
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

object MovieFixture {

    val movie = Movie(
        "해리포터와 마법사의 돌",
        "https://tinyurl.com/mjn9ntrz",
        LocalDateTime.of(2025, 4, 1, 0, 0, 0),
        LocalDateTime.of(2025, 4, 25, 23, 59, 59),
        152.minutes,
    )
}