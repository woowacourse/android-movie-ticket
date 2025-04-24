package woowacourse.movie.presentation.movies

import woowacourse.movie.domain.model.Movie

interface MoviesContract {
    interface View {
        fun showMovies(movies: List<Movie>)
        fun navigateToBooking(movie: Movie)
    }

    interface Presenter {
        fun onViewCreated()
        fun onMovieClicked(movie: Movie)
    }
}