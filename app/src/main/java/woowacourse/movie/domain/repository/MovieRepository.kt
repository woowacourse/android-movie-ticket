package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Movie

interface MovieRepository {
    fun loadMovies(): List<Movie>

    fun getMovie(id: Int): Movie
}
