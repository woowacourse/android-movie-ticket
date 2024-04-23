package woowacourse.movie.screen.main

interface MainContract {
    interface View {
        fun displayMovies(movies: List<MovieModel>)

        fun navigateToReservation(id: Long)
    }

    interface Presenter {
        fun onStart()

        fun onMovieSelected(id: Long)
    }
}
