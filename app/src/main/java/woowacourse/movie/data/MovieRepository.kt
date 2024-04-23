package woowacourse.movie.data

import woowacourse.movie.model.Movie

interface MovieRepository {
    fun loadMovies(): List<Movie>

    fun findMovieById(id: Long): Movie?
}
