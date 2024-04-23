package woowacourse.movie.screeningmovie

import woowacourse.movie.repository.MovieRepository

class ScreenMoviePresenter(
    private val view: ScreeningMovieView,
    private val repository: MovieRepository,
) {
    init {
        view.showMovies(repository.screenMovies().map { it.toScreenMovieUiModel() })
    }

    fun startReservation(screeningMovieId: Long) {
        val screenMovie = repository.screenMovieById(screeningMovieId)
        view.onClickReservationButton(screenMovie.id)
    }
}
