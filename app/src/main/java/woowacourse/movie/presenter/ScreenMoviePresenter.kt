package woowacourse.movie.presenter

import android.util.Log
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.view.ScreeningMovieView

class ScreenMoviePresenter(
    private val view: ScreeningMovieView,
    private val repository: MovieRepository,
) {
    init {
        view.showMovies(repository.screenMovies().map { it.toScreenMovieUiModel() })
    }

    fun startReservation(id: Long) {
        val screenMovie = repository.screenMovieById(id)
        Log.d("로그", "ScreenMoviePresenter - navigateToMovieReservationView() called $id")
        Log.d("로그", "ScreenMoviePresenter - navigateToMovieReservationView() called $screenMovie")
        view.onClickReservationButton(screenMovie.id)
    }
}
