package woowacourse.movie.presentation.screening

interface ScreeningMovieView {
    fun showMovies(movies: List<ScreeningMovieUiModel>)

    fun navigateToReservationView(movieId: Long)

    fun navigateToAdView()

    fun showErrorView()
}
