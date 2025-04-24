package woowacourse.movie.presentation.view.movies

import woowacourse.movie.presentation.fixture.dummyMovie
import woowacourse.movie.presentation.model.toUiModel

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun fetchData() {
        val movies = listOf(dummyMovie.toUiModel())
        view.setScreen(movies)
    }
}
