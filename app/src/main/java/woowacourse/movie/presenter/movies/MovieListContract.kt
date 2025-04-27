package woowacourse.movie.presenter.movies

import woowacourse.movie.view.movies.model.UiModel

interface MovieListContract {
    interface View {
        fun showMovieList(movieList: List<UiModel>)
    }

    interface Presenter {
        fun loadUiData()
    }
}
