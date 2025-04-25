package woowacourse.movie.feature.movies.presenter

import woowacourse.movie.domain.model.Movie.Companion.movies
import woowacourse.movie.feature.mapper.toUi
import woowacourse.movie.feature.model.MovieUiModel
import woowacourse.movie.feature.movies.contract.MoviesContract

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun onCreateView() {
        view.showMovies(movies.map { it.toUi() })
    }

    override fun onMovieBookingClicked(movie: MovieUiModel) {
        view.navigateToBookingDetail(movie)
    }
}
