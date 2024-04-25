package woowacourse.movie.contract

import woowacourse.movie.model.movie.Movie

interface MovieListContract {
    interface View {
        fun displayMovies(movies: List<Movie>)

        fun navigateToMovieDetail(movieId: Int)
    }

    interface Presenter {
        fun loadMovies()

        fun selectMovie(movieId: Int)
    }
}
