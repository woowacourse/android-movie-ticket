package woowacourse.movie.model.data

import java.time.LocalDate

object MovieRepository {
    fun getMovies(): List<Movie> =
        listOf(
            Movie(
                "승부",
                LocalDate.of(2025, 3, 26),
                LocalDate.of(2025, 4, 26),
                115,
                "match",
            ),
            Movie(
                "미키 17",
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 29),
                137,
                "mickey1",
            ),
        )
}
