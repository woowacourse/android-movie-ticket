package woowacourse.movie.repository

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Screen

object DummyScreenList : ScreenListRepository {
    override val list: List<Screen>
        get() = _list.toList()

    private val _list: MutableList<Screen> = mutableListOf()

    init {
        repeat(100) {
            addToList(DummyMovieList.list)
        }
    }

    override fun listSize(): Int = _list.size

    override fun addToList(movie: Movie) {
        _list.add(Screen.from(movie))
    }

    override fun addToList(screen: Screen) {
        _list.add(screen)
    }

    override fun addToList(movieList: List<Movie>) {
        movieList.forEach { movie ->
            addToList(movie)
        }
    }

    override fun find(id: Long): Screen {
        val screen = findOrNull(id)
        require(screen != null) { "There is no such Screen in database!" }
        return screen
    }

    override fun findOrNull(id: Long): Screen? {
        val filteredList = list.filter { it.id == id }
        return if (filteredList.size == 1) filteredList[0] else null
    }
}
