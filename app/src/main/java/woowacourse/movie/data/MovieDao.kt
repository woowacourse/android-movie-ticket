package woowacourse.movie.data

import woowacourse.movie.model.Movie

class MovieDao {
    private val movies: List<Movie> = MovieDummy.movies()

    fun find(movieId: Int): Movie {
        return movies[movieId]
    }

    fun findAll(): List<Movie> = movies
}
