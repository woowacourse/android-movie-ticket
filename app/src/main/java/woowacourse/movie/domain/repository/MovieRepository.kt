package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Movie

interface MovieRepository {
    fun getAllMovies(): List<Movie>

    fun getMovie(id: Int): Movie
}
