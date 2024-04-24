package woowacourse.movie.data

import woowacourse.movie.model.Movie

interface MovieRepository {
    fun findAll(): List<Movie>

    fun find(id: Long): Movie?
}
