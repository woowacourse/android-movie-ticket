package woowacourse.movie.presentation.screen

import woowacourse.movie.domain.model.Movie

interface MovieScreenContract {
    interface View {
        fun showScreenData(
            movies: List<Movie>,
            ads: List<String>,
        )

        fun moveToReservation(movieId: Int)
    }

    interface Presenter {
        fun loadScreenData()

        fun startReservation(movieId: Int)
    }
}
