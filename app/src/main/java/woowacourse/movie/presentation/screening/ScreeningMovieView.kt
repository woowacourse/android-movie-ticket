package woowacourse.movie.presentation.screening

interface ScreeningMovieView {
    fun showMovies(movies: List<ScreeningMovieUiModel>)

    fun onClickReservationButton(reservationId: Long)
}
