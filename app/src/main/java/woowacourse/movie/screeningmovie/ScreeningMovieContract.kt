package woowacourse.movie.screeningmovie

interface ScreeningMovieContract {
    interface View {
        fun showMovies(movies: List<ScreenMovieUiModel>)

        fun onClickReservationButton(screeningMovieId: Long)
    }

    interface Presenter {
        fun startReservation(screeningMovieId: Long)
    }
}
