package woowacourse.movie.repository

import woowacourse.movie.domain.movie.Movie

interface MovieListRepository {
    val list: List<Movie>

    fun listSize(): Int

    /**
     * This function might occur an Exception if there is no such movie in database
     *
     * @param id Movie id value that you want to find
     * @return Movie which matches with id value in database
     */
    fun find(id: Long): Movie

    fun find(title: String): Movie

    fun findOrNull(id: Long): Movie?

    fun findOrNull(title: String): Movie?
}
