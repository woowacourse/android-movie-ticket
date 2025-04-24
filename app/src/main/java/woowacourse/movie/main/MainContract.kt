package woowacourse.movie.main

import woowacourse.movie.domain.Movie

interface MainContract {
    interface View {
        fun showMovies(movies: List<Movie>)
    }

    interface Presenter {
        fun initMovies()
    }
}
