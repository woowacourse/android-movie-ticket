package woowacourse.movie.main

import woowacourse.movie.model.UiMovie

interface MainContract {
    interface View {
        fun displayMovies(uiMovies: List<UiMovie>)

        fun navigateToReservation(uiMovie: UiMovie)
    }

    interface Presenter {
        fun onViewCreated()

        fun onMovieSelected(position: Int)
    }
}
