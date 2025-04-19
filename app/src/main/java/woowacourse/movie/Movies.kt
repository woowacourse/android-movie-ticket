package woowacourse.movie

import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import java.time.LocalDate

class Movies {
    fun getAll(): List<Movie> {
        return createMovies(100)
    }

    private fun createMovies(number: Int): List<Movie> {
        val movies: MutableList<Movie> = mutableListOf()
        for (index in 1..number) {
            val harry = Movie(
                R.drawable.harry,
                "해리 포터와 마법사의 돌 $index",
                Date(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
                152,
            )
            movies.add(harry)
        }
        return movies.toList()
    }
}