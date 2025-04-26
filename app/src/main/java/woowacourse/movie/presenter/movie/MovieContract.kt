package woowacourse.movie.presenter.movie

import woowacourse.movie.ui.model.movie.MovieUiModel

interface MovieContract {
    interface Presenter {
        fun loadMovies()

        fun onMovieSelect(movieUiModel: MovieUiModel)
    }

    interface View {
        fun showMovies(movieUiModels: List<MovieUiModel>)

        fun showErrorMessage(message: String)

        fun moveTo(movieUiModel: MovieUiModel)
    }
}
