package woowacourse.movie.view.movie

import woowacourse.movie.view.movie.model.MovieUiModel

interface MovieListContract {
    interface View {
        fun showMovieList(movies: List<MovieUiModel>)
    }

    interface Presenter {
        fun loadMovieListScreen()
    }
}
