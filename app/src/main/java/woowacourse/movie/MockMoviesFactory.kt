package woowacourse.movie

import woowacourse.movie.domain.DateRange
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies
import java.time.LocalDate

object MockMoviesFactory {
    fun generateMovies(): Movies {
        return Movies(
            listOf(generateMovie())
        )
    }

    private fun generateMovie(): Movie {
        return Movie(
            R.drawable.poster_harrypotter.toString(),
            "해리 포터",
            DateRange(
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 31),
            ),
            153,
            "adsfasdfadsf",
        )
    }
}
