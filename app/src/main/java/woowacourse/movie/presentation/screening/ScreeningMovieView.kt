package woowacourse.movie.presentation.screening

interface ScreeningMovieView {
    fun updateMovies(movies: List<ScreeningMovieUiModel>)

    fun navigateToReservationView(movieId: Long)

    fun showErrorView()
}
