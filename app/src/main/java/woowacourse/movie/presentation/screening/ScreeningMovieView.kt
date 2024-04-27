package woowacourse.movie.presentation.screening

interface ScreeningMovieView {
    fun showMovies(movies: List<ScreeningMovieUiModel>)

    fun showErrorView()

    fun navigateToReservationView(movieId: Long)

    fun navigateToAdView()
}
