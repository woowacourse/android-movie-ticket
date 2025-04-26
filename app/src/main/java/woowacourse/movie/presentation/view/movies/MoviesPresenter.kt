package woowacourse.movie.presentation.view.movies

import woowacourse.movie.presentation.fixture.createDummyMovies
import woowacourse.movie.presentation.model.toUiModel

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun fetchData() {
        val movies = createDummyMovies(10_000).map { it.toUiModel() }
        view.setScreen(movies)
    }
}
