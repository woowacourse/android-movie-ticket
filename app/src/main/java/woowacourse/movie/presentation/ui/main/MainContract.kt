package woowacourse.movie.presentation.ui.main

import woowacourse.movie.domain.model.Movie

interface MainContract {
    interface View {
        fun showMovieList(movieList: List<Movie>)

        fun moveToMovieDetail(movie: Movie)

        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadMovieList()

        fun requestMovieDetail(movie: Movie)
    }
}
