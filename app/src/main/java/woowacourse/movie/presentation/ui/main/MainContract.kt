package woowacourse.movie.presentation.ui.main

import woowacourse.movie.presentation.uimodel.MovieUiModel

interface MainContract {
    interface View {
        fun showMovieList(movieList: List<MovieUiModel>)

        fun moveToMovieDetail(movieId: Int)

        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadMovieList()

        fun requestMovieDetail(movieId: Int)
    }
}
