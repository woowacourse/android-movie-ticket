package woowacourse.movie.view.movie

import woowacourse.movie.model.Movie

interface MovieContract {
    interface View {
        fun updateView(movies: List<Movie>)

        fun navigateToReservationComplete(movie: Movie)
    }

    interface Presenter {
        fun fetchMovies()
    }
}
