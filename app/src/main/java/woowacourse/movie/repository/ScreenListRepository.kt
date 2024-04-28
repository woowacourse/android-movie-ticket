package woowacourse.movie.repository

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Screen

interface ScreenListRepository {
    val list: List<Screen>

    fun listSize(): Int

    fun addToList(movie: Movie)

    fun addToList(screen: Screen)

    fun addToList(movieList: List<Movie>)

    /**
     * This function might occur an Exception if there is no such screen data in database
     *
     * @param id Screen id value that you want to find
     * @return Screen object which matches with id value in database
     */

    fun find(id: Long): Screen

    fun findOrNull(id: Long): Screen?
}
