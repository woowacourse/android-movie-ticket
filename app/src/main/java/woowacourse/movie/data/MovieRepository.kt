package woowacourse.movie.data

import woowacourse.movie.domain.screening.Movie

interface MovieRepository {
    fun findAll(): List<Movie>

    fun find(id: Long): Movie?
}
