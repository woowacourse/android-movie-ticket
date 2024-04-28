package woowacourse.movie.repository

import woowacourse.movie.R
import woowacourse.movie.domain.movie.Ads
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Screen
import woowacourse.movie.domain.movie.ScreenView

object DummyScreenList : ScreenListRepository {
    override val list: List<ScreenView>
        get() = _list.toList()

    private val _list: MutableList<ScreenView> = mutableListOf()

    init {
        repeat(100) {
            addToList(DummyMovieList.list)
            addToList(Ads(imgRes = R.drawable.img_ad_woowa))
        }
    }

    override fun listSize(): Int = _list.size

    override fun addToList(movie: Movie) {
        _list.add(Screen.from(movie))
    }

    override fun addToList(screenView: ScreenView) {
        _list.add(screenView)
    }

    override fun addToList(movieList: List<Movie>) {
        movieList.forEach { movie ->
            addToList(movie)
        }
    }

    override fun find(id: Long): ScreenView {
        val screen = findOrNull(id)
        require(screen != null) { "There is no such Screen in database!" }
        return screen
    }

    override fun findOrNull(id: Long): ScreenView? {
        val filteredList = list.filter { it.id == id }
        return if (filteredList.size == 1) filteredList[0] else null
    }
}
