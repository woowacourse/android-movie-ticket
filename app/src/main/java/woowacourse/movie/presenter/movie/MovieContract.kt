package woowacourse.movie.presenter.movie

import woowacourse.movie.domain.model.ads.Ads
import woowacourse.movie.ui.model.movie.MovieUiModel

interface MovieContract {
    interface Presenter {
        fun loadMovies()

        fun onMovieSelect(movieUiModel: MovieUiModel)
    }

    interface View {
        fun showMovies(
            movieUiModels: List<MovieUiModel>,
            ads: Ads,
        )

        fun showErrorMessage(message: String)

        fun moveTo(movieUiModel: MovieUiModel)
    }
}
