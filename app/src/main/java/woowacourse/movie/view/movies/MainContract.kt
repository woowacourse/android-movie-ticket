package woowacourse.movie.view.movies

import woowacourse.movie.domain.Movie

interface MainContract {
    interface Presenter {
        fun fetchData()
    }

    interface View {
        fun showMoviesScreen(
            movies: List<Movie>,
            navigate: (Movie) -> Unit,
        )

        fun navigateToReservation(movie: Movie)
    }
}
