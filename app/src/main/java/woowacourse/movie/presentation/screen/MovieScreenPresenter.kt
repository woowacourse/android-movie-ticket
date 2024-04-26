package woowacourse.movie.presentation.screen

import woowacourse.movie.domain.repository.AdRepository
import woowacourse.movie.domain.repository.MovieRepository

class MovieScreenPresenter(
    private val view: MovieScreenContract.View,
    private val movieRepository: MovieRepository,
    private val adRepository: AdRepository,
) : MovieScreenContract.Presenter {
    override fun loadScreenMovies() {
        val movies = movieRepository.getMovies()
        view.showScreenMovies(movies)
    }

    override fun startReservation(movieId: Int) {
        view.moveToReservation(movieId)
    }

    override fun requestAd(ad: (String) -> Unit) {
       ad(adRepository.getRandomAd())
    }

    companion object {
        const val KEY_NAME_MOVIE = "movie"
    }
}
