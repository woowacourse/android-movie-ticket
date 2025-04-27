package woowacourse.movie.presenter.booking

import woowacourse.movie.ui.model.movie.MovieUiModel

interface BookingContract {
    interface Presenter {
        fun loadMovie(movieUiModel: MovieUiModel)
    }

    interface View {
        fun showMovieInfo(movieUiModel: MovieUiModel)

        fun showErrorMessage(message: String)
    }
}
