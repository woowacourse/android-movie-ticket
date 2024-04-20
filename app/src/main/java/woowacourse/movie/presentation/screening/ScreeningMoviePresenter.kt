package woowacourse.movie.presentation.screening

import woowacourse.movie.repository.MovieRepository

class ScreeningMoviePresenter(
    private val view: ScreeningMovieView,
    private val repository: MovieRepository,
) {
    init {
        view.updateMovies(repository.screenMovies().map { it.toScreenMovieUiModel() })
    }

    fun startReservation(id: Long) {
        val screenMovie = repository.screenMovieById(id)
        view.navigateToReservationView(screenMovie.id)
    }
}
