package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Movie
import java.time.LocalDate

class MovieRepository {
    fun getAllMovies(): List<Movie> =
        listOf(
            Movie(
                "승부",
                LocalDate.of(2025, 3, 26),
                LocalDate.of(2025, 4, 26),
                115,
                1,
            ),
            Movie(
                "미키 17",
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 29),
                137,
                2,
            ),
            Movie(
                "야당",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 29),
                123,
                3,
            ),
            Movie(
                "범죄도시",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 29),
                135,
                4,
            ),
        )

    fun getMovieById(movieId: Int): Movie = getAllMovies()[movieId - 1]
}
