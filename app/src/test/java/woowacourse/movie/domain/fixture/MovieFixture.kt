package woowacourse.movie.domain.fixture

import woowacourse.movie.domain.model.booking.ScreeningDate
import woowacourse.movie.domain.model.movies.Movie
import java.time.LocalDate

val MoviesFixture =
    listOf(
        Movie(
            title = "테스트 영화 1",
            posterResource = "",
            releaseDate =
                ScreeningDate(
                    LocalDate.of(2025, 4, 24),
                    LocalDate.of(2025, 5, 24),
                ),
            runningTime = 120,
        ),
        Movie(
            title = "테스트 영화 2",
            posterResource = "",
            releaseDate =
                ScreeningDate(
                    LocalDate.of(2025, 4, 24),
                    LocalDate.of(2025, 5, 24),
                ),
            runningTime = 110,
        ),
    )
