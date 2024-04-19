package woowacourse.movie.presenter

import woowacourse.movie.model.Movie

interface MovieMainContract {
    interface View {
        fun displayMovies(movies: List<Movie>)
    }

    interface Presenter {
        fun loadMovies()
    }
}
