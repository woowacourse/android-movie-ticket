package woowacourse.movie.presenter.movies

import woowacourse.movie.data.MovieStore
import woowacourse.movie.domain.model.ad.Advertisement
import woowacourse.movie.view.movies.model.UiModel
import woowacourse.movie.view.movies.model.toUiModel

class MovieListPresenter(
    private val view: MovieListContract.View,
    private val model: MovieStore,
) : MovieListContract.Presenter {
    override fun loadUiData() {
        val items = mutableListOf<UiModel>()
        model.getAll().forEachIndexed { index, movie ->
            items.add(movie.toUiModel())
            if ((index + 1) % AD_DIVIDE_STANDARD == 0) {
                items.add(Advertisement().toUiModel())
            }
        }

        view.showMovieList(items)
    }

    companion object {
        private const val AD_DIVIDE_STANDARD = 3
    }
}
