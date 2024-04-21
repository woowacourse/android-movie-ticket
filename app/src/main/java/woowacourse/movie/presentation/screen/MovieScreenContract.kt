package woowacourse.movie.presentation.screen

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.screen.model.MovieModel

interface MovieScreenContract {
    interface View {
        fun showScreenMovies(movies: List<Movie>)
        fun moveToReservation(movieModel: MovieModel)
    }

    interface Presenter {
        fun loadScreenMovies()
        fun clickReservationButton(
            title: String,
            posterResourceId: Int,
            screeningDate: String,
            runningTime: Int,
            description: String,
        )
    }
}
