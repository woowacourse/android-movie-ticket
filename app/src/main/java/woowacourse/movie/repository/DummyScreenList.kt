package woowacourse.movie.repository

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Screen

object DummyScreenList : ScreenListRepository {
    override val list: List<Screen>
        get() = _list.toList()

    private val _list: MutableList<Screen> = mutableListOf()

    init {
        addToList(DummyMovieList.list)
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
}
