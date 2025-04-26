package woowacourse.movie.presenter.movies

import woowacourse.movie.domain.model.ad.Advertisement
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.view.movies.model.UiModel
import woowacourse.movie.view.movies.model.toUiModel

class MovieListPresenter(
    private val view: MovieListContract.View,
    private val model: List<Movie>,
) : MovieListContract.Presenter {
    private val items = mutableListOf<UiModel>()

    override fun loadUiData() {
        model.forEachIndexed { index, movie ->
            items.add(movie.toUiModel())
            if ((index + 1) % AD_DIVIDE_STANDARD == 0) {
                items.add(Advertisement().toUiModel())
            }
        }
        view.showMovieList(items)
    }

    override fun onSelectMovie(movieIdx: Int) {
        val movieIndex =
            items
                .subList(0, movieIdx)
                .count { it is UiModel.MovieUiModel }
        view.moveToBookingComplete(movieIndex)
    }

    companion object {
        private const val AD_DIVIDE_STANDARD = 3
    }
}
