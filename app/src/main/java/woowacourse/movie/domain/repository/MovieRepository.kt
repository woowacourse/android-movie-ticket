package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Movie

interface MovieRepository {
    fun loadMovies(): List<Movie>
}
