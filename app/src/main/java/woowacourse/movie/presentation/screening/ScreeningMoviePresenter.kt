package woowacourse.movie.presentation.screening

import woowacourse.movie.repository.MovieRepository

class ScreeningMoviePresenter(
    private val view: ScreeningMovieView,
    private val repository: MovieRepository,
) {
    fun loadScreenMovies() {
        view.showMovies(repository.screenMovies().map { it.toScreenMovieUiModel() })
    }

    fun startReservation(id: Long) {
        val screeningMovie = repository.screenMovieById(id) ?: return view.showErrorView()
        view.navigateToReservationView(screeningMovie.id)
    }

    fun startAd() {
        view.navigateToAdView()
    }
}
