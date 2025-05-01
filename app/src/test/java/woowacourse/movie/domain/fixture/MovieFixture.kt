package woowacourse.movie.domain.fixture

import woowacourse.movie.domain.model.movies.Movie

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
