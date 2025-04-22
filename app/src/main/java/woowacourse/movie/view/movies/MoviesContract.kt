package woowacourse.movie.view.movies

import woowacourse.movie.domain.model.Movie

interface MoviesContract {
    interface Presenter {
        fun fetchData()
    }

    interface View {
        fun setScreen(
            movies: List<Movie>,
            navigateToReservationScreen: (Movie) -> Unit,
        )

        fun navigateToReservationScreen(movie: Movie)
    }
}
