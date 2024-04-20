package woowacourse.movie.db

import woowacourse.movie.model.Movie

class MovieDao {
    private val movies: List<Movie> = MovieDatabase.movies

    fun find(movieId: Int): Movie {
        return movies[movieId]
    }

    fun findAll(): List<Movie> = movies
}
