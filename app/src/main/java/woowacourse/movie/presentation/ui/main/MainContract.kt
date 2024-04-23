package woowacourse.movie.presentation.ui.main

import woowacourse.movie.presentation.dto.MovieViewModel

interface MainContract {
    interface View {
        fun showMovieList(movieList: List<MovieViewModel>)

        fun moveToMovieDetail(movieId: Int)

        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadMovieList()

        fun requestMovieDetail(movieId: Int)
    }
}
