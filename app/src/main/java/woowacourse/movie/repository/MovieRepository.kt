package woowacourse.movie.repository

import woowacourse.movie.domain.Movie

interface MovieRepository {

    fun findAll(): List<Movie>
}
