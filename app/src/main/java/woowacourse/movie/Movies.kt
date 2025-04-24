package woowacourse.movie

import woowacourse.movie.domain.Movie

interface Movies {
    interface View {
        fun showMovies(movies: List<Movie>)
        fun navigateToBook(movie: Movie)
    }

    interface Presenter {
        fun loadMovies()
        fun selectedMovie(movie: Movie)
    }
}
