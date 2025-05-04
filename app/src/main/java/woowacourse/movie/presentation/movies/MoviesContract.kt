package woowacourse.movie.presentation.movies

import woowacourse.movie.domain.model.movie.Movie

interface MoviesContract {
    interface View {
        fun showMovies(items: List<MoviesItem>)

        fun navigateToBooking(movie: Movie)
    }

    interface Presenter {
        fun onViewCreated()

        fun onMovieClicked(movie: Movie)
    }
}
