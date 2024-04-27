package woowacourse.movie.feature.main

import woowacourse.movie.feature.main.ui.ScreeningModel

interface MainContract {
    interface View {
        fun displayMovies(movies: List<ScreeningModel>)

        fun navigateToReservationScreen(id: Long)
    }

    interface Presenter {
        fun fetchMovieList()

        fun selectMovie(id: Long)
    }
}
