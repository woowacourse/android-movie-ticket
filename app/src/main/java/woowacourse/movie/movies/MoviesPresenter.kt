package woowacourse.movie.movies

import woowacourse.movie.domain.movies

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun loadMovies() {
        val uiModels = movies.map { it.toMovieUiModel() }
        view.showMovies(uiModels)
    }

    override fun onMovieSelected(movieUiModel: MovieUiModel) {
        view.navigateToBookingDetail(movieUiModel)
    }
}
