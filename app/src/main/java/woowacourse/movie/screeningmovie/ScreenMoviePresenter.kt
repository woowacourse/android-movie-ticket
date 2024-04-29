package woowacourse.movie.screeningmovie

import woowacourse.movie.repository.MovieRepository

class ScreenMoviePresenter(
    private val view: ScreeningMovieContract.View,
    private val repository: MovieRepository,
) : ScreeningMovieContract.Presenter {
    init {
        view.showMovies(repository.screenMovies().map { it.toScreenMovieUiModel() })
    }

    override fun startReservation(screeningMovieId: Long) {
        val screenMovie = repository.screenMovieById(screeningMovieId)
        view.onClickReservationButton(screenMovie.id)
    }
}
