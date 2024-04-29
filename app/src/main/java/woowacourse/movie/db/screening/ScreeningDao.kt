package woowacourse.movie.db.screening

import woowacourse.movie.model.movie.Movie

class ScreeningDao {
    private val movies: List<Movie> = ScreeningDatabase.movies

    fun find(movieId: Int): Movie {
        return movies[movieId]
    }

    fun findAll(): List<Movie> = movies
}
