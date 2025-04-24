package woowacourse.movie.domain.model

import java.time.LocalDate

class ScreeningMovies {
    fun getData(): List<Movie> = listOf(
        Movie(
            "승부",
            LocalDate.of(2025, 3, 26),
            LocalDate.of(2025, 4, 26),
            115,
        ),
        Movie(
            "미키 17",
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 29),
            137,
        ),
    )
}