package woowacourse.movie.presentation.screen

import woowacourse.movie.domain.repository.AdRepository
import woowacourse.movie.domain.repository.MovieRepository

class MovieScreenPresenter(
    private val view: MovieScreenContract.View,
    private val movieRepository: MovieRepository,
    private val adRepository: AdRepository,
) : MovieScreenContract.Presenter {
    override fun loadScreenData() {
        val movies = movieRepository.getMovies()
        val ads = adRepository.getAds()
        view.showScreenData(movies, ads)
    }

    override fun startReservation(movieId: Int) {
        view.moveToReservation(movieId)
    }

    companion object {
        const val KEY_NAME_MOVIE = "movie"
    }
}
