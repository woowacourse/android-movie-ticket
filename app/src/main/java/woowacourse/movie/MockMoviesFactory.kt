package woowacourse.movie

import domain.DateRange
import domain.Movie
import domain.Movies
import java.time.LocalDate

object MockMoviesFactory {
    fun generateMovies(): domain.Movies {
        return domain.Movies(
            listOf(generateMovie())
        )
    }

    private fun generateMovie(): domain.Movie {
        return domain.Movie(
            R.drawable.poster_harrypotter.toString(),
            "해리 포터",
            domain.DateRange(
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 31),
            ),
            153,
            "adsfasdfadsf",
        )
    }
}
