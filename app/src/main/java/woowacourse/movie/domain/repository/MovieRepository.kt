package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Movie
import java.time.LocalDate

object MovieRepository {
    val movies: List<Movie> =
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
        )

    fun getMovieById(movieId: Int): Movie = movies[movieId - 1]
}
