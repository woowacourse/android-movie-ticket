package woowacourse.movie.screen.main

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
