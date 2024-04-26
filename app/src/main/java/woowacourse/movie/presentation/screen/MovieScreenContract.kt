package woowacourse.movie.presentation.screen

import woowacourse.movie.domain.model.Movie

interface MovieScreenContract {
    interface View {
        fun showScreenMovies(movies: List<Movie>)

        fun moveToReservation(movieId: Int)
    }

    interface Presenter {
        fun loadScreenMovies()

        fun startReservation(movieId: Int)

        fun requestAd(ad:(String)-> Unit)
    }
}
