package woowacourse.movie.fixture

import woowacourse.movie.domain.Movie
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.time.Duration.Companion.minutes

object DomainFixture {
    val movie =
        Movie(
            "해리포터와 마법사의 돌",
            "images/movie_poster.jpg",
            LocalDateTime.of(2025, 4, 3, 0, 0, 0),
            LocalDateTime.of(2025, 4, 5, 23, 59, 59),
            125.minutes,
        )
    val weekEndRule =
        listOf(
            LocalTime.of(9, 0, 0),
            LocalTime.of(11, 0, 0),
            LocalTime.of(13, 0, 0),
            LocalTime.of(15, 0, 0),
            LocalTime.of(17, 0, 0),
            LocalTime.of(19, 0, 0),
            LocalTime.of(21, 0, 0),
            LocalTime.of(23, 0, 0),
        )

    val weekDayRule =
        listOf(
            LocalTime.of(10, 0, 0),
            LocalTime.of(12, 0, 0),
            LocalTime.of(14, 0, 0),
            LocalTime.of(16, 0, 0),
            LocalTime.of(18, 0, 0),
            LocalTime.of(20, 0, 0),
            LocalTime.of(22, 0, 0),
            LocalTime.of(0, 0, 0),
        )
}
