package woowacourse.movie.ui.main

import woowacourse.movie.ui.model.MovieItem

interface MainContract {
    interface Presenter {
        fun showMovies()
    }

    interface View {
        fun showMovies(moviesItem: List<MovieItem>)
    }
}
