package woowacourse.movie.domain.movies

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
                LocalDate.of(2025, 5, 2),
                LocalDate.of(2025, 5, 29),
                135,
                4,
            ),
        )

    fun getMovieById(movieId: Int): Movie = getAllMovies()[movieId - 1]

    fun getMovieListItems(): List<MovieListItem> {
        val result = mutableListOf<MovieListItem>()
        getAllMovies().forEachIndexed { index, movie ->
            result.add(MovieListItem.MovieItem(movie))
            if ((index + 1) % 3 == 0) {
                result.add(MovieListItem.AdvertisementItem)
            }
        }
        return result
    }
}
