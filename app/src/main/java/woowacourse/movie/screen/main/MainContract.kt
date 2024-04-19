package woowacourse.movie.screen.main

import woowacourse.movie.model.Movie

interface MainContract {
    interface View {
        fun displayMovies(movies: List<Movie>)

        fun navigateToReservation(movie: Movie)
    }

    interface Presenter {
        fun onStart()

        fun onMovieSelected(movie: Movie)
    }
}
