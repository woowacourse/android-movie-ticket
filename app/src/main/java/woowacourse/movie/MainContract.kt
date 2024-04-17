package woowacourse.movie

import woowacourse.movie.model.Movie

interface MainContract {
    interface View {
        fun displayMovies(movies: MutableList<Movie>)

        fun navigateToReservation(movie: Movie)
    }

    interface Presenter {
        fun onViewCreated()

        fun onMovieSelected(movie: Movie)
    }
}
