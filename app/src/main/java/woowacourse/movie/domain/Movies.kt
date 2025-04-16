package woowacourse.movie.domain

import woowacourse.movie.R
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

data class Movies(
    val movies: Map<String, Movie>,
) {
    fun find(title: String): Movie {
        return movies[title] ?: throw IllegalStateException(ERR_MOVIE_NOT_FOUND)
    }

    fun toList(): List<Movie> = movies.values.toList()

    companion object {
        private const val ERR_MOVIE_NOT_FOUND = "영화가 존재하지 않습니다."
        val movies = listOf<Movie>(Movie("해리포터와 마법사의 돌", R.drawable.movie_poster,  LocalDateTime.of(2025, 4, 1, 0, 0, 0),
            LocalDateTime.of(2025, 4, 25, 23, 59, 59),
            125.minutes))
    }
}