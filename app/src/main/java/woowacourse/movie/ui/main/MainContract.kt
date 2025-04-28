package woowacourse.movie.ui.main

import woowacourse.movie.domain.model.movie.Movie

interface MainContract {
    interface Presenter {
        fun showMovies()
    }

    interface View {
        fun showMovies(
            movies: List<Movie>,
            advertisements: List<Int>,
        )
    }
}
