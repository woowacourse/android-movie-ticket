package woowacourse.movie.presenter

import woowacourse.movie.model.Movie

interface MainContract {
    interface View {
        fun updateMovieList(movies: List<Movie>)
    }

    interface Presenter {
        fun fetchMovies()
    }
}
