package woowacourse.movie.screeningmovie

interface ScreeningMovieView {
    fun showMovies(movies: List<ScreenMovieUiModel>)

    fun onClickReservationButton(screeningMovieId: Long)
}
