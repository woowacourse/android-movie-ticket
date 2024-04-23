package woowacourse.movie.presentation.movieList

import woowacourse.movie.model.Movie

interface MovieListContract {
    interface View {
        fun displayMovies(movies: List<Movie>)

        fun navigate(movieId: Int)
    }

    interface Presenter {
        fun loadMovies()
    }
}
