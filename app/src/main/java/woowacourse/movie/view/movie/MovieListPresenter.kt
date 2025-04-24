package woowacourse.movie.view.movie

import woowacourse.movie.common.DummyData
import woowacourse.movie.view.movie.model.toUiModel

class MovieListPresenter(
    private val view: MovieListContract.View,
) : MovieListContract.Presenter {
    override fun loadMovieListScreen() {
        val movies = DummyData.movies.map { it.key.toUiModel() }
        view.showMovieList(movies)
    }
}
