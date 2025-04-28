package woowacourse.movie.presentation.view.movies

import woowacourse.movie.presentation.model.MovieUiModel

interface MoviesContract {
    interface Presenter {
        fun fetchData()
    }

    interface View {
        fun showScreen(movies: List<MovieUiModel>)
    }
}
