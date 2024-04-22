package woowacourse.movie.main.presenter.contract

import woowacourse.movie.main.model.Movie

interface MovieMainContract {
    interface View {
        fun displayMovies(movies: List<Movie>)

        fun navigateToDetailView(id: Long)
    }

    interface Presenter {
        fun loadMovies()
    }
}
