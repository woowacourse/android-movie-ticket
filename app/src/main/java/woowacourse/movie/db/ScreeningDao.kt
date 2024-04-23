package woowacourse.movie.db

import woowacourse.movie.model.Movie

class ScreeningDao {
    private val movies: List<Movie> = ScreeningDatabase.movies

    fun find(movieId: Int): Movie {
        return movies[movieId]
    }

    fun findAll(): List<Movie> = movies
}
