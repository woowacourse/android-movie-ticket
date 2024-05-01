package woowacourse.movie.repository.movie

import woowacourse.movie.model.movie.Movie

interface MovieRepository {
    fun getMovies(): List<Movie>

    fun getMovie(movieId: Int): Movie
}
