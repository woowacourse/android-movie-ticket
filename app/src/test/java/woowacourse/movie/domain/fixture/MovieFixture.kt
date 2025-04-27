package woowacourse.movie.domain.fixture

import woowacourse.movie.domain.model.booking.ScreeningDate
import woowacourse.movie.domain.model.movies.Movie
import java.time.LocalDate

val screeningDateFixture =
    ScreeningDate(
        startDate = LocalDate.of(2025, 5, 1),
        endDate = LocalDate.of(2025, 5, 25),
    )
val moviesFixture =
    listOf(
        Movie(
            id = 0,
            title = "Movie 1",
            posterResource = "poster1",
            releaseDate = screeningDateFixture,
            runningTime = 120,
        ),
        Movie(
            id = 1,
            title = "Movie 2",
            posterResource = "poster2",
            releaseDate = screeningDateFixture,
            runningTime = 120,
        ),
        Movie(
            id = 2,
            title = "Movie 3",
            posterResource = "poster3",
            releaseDate = screeningDateFixture,
            runningTime = 120,
        ),
    )
