package woowacourse.movie.screeningmovie

interface ScreeningMovieContract {
    interface View {
        fun showMovies(movies: List<ScreeningItem>)

        fun onClickReservationButton(screeningMovieId: Long)
    }

    interface Presenter {
        fun startReservation(screeningMovieId: Long)

        fun loadScreeningMovies()
    }
}
