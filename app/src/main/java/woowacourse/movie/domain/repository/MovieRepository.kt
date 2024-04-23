package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Movie

interface MovieRepository {
    fun getMovies(): List<Movie>

    fun getMovie(id: Int): Movie
}
