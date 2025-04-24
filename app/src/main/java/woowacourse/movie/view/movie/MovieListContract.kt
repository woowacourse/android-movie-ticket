package woowacourse.movie.view.movie

import woowacourse.movie.domain.Movie

interface MovieListContract {
    interface View {
        fun showMovieList(movies: List<Movie>)
    }

    interface Presenter {
        fun loadMovieListScreen()
    }
}
