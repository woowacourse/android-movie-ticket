package woowacourse.movie.ui.view.movies

import woowacourse.movie.domain.model.Movie

interface MovieContract {
    interface View {
        fun showAllMovies(movies: List<Movie>)
    }

    interface Presenter {
        fun loadAllMovies()
    }
}
