package woowacourse.movie.screeningmovie

import woowacourse.movie.model.ScreeningMovies
import woowacourse.movie.repository.MovieRepository

class ScreenMoviePresenter(
    private val view: ScreeningMovieContract.View,
    private val repository: MovieRepository,
) : ScreeningMovieContract.Presenter {
    override fun startReservation(screeningMovieId: Long) {
        val screenMovie = repository.screenMovieById(screeningMovieId)
        view.onClickReservationButton(screenMovie.id)
    }

    override fun loadScreeningMovies() {
        val screeningMovies = repository.screenMovies()
        val screeningMoviesWithAds =
            ScreeningMovies(screeningMovies).insertAdvertisements(
                ADVERTISEMENT_INTERVAL,
            )
        view.showMovies(screeningMoviesWithAds.toScreenItems())
    }

    companion object {
        private const val ADVERTISEMENT_INTERVAL = 3
    }
}
