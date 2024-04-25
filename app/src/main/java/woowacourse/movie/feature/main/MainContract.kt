package woowacourse.movie.feature.main

import woowacourse.movie.feature.main.ui.MovieModel

interface MainContract {
    interface View {
        fun displayMovies(movies: List<MovieModel>)

        fun navigateToReservationScreen(id: Long)
    }

    interface Presenter {
        fun fetchMovieList()

        fun selectMovie(id: Long)
    }
}
