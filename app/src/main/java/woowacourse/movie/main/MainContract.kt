package woowacourse.movie.main

import woowacourse.movie.model.Movie

interface MainContract {
    interface View {
        fun displayMovies(movies: List<Movie>)

        fun navigateToReservation(movie: Movie)
    }

    interface Presenter {
        fun onViewCreated()

        fun onMovieSelected(movie: Movie)
    }
}
