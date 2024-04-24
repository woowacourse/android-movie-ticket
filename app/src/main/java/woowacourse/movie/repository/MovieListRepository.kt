package woowacourse.movie.repository

import woowacourse.movie.domain.movie.Movie

interface MovieListRepository {
    val list:List<Movie>

    fun listSize(): Int
    fun findOrNull(id: Long): Movie?

    fun findOrNull(title: String): Movie?
}