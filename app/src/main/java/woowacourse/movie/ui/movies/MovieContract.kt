package woowacourse.movie.ui.movies

import woowacourse.movie.domain.movies.Movie

interface MovieContract {
    interface View {
        fun showAllMovies(movies: List<Movie>)
    }

    interface Presenter {
        fun loadAllMovies()
    }
}
