package woowacourse.movie.repository

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.ScreenView

interface ScreenListRepository {
    val list: List<ScreenView>

    fun listSize(): Int

    fun addToList(movie: Movie)

    fun addToList(screenView: ScreenView)

    fun addToList(movieList: List<Movie>)

    /**
     * This function might occur an Exception if there is no such screen data in database
     *
     * @param id Screen id value that you want to find
     * @return Screen object which matches with id value in database
     */

    fun find(id: Long): ScreenView

    fun findOrNull(id: Long): ScreenView?
}
