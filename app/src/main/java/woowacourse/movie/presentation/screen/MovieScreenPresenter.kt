package woowacourse.movie.presentation.screen

import woowacourse.movie.domain.repository.MovieRepository

class MovieScreenPresenter(
    private val view: MovieScreenContract.View,
    private val movieRepository: MovieRepository,
) : MovieScreenContract.Presenter {
    override fun loadScreenMovies() {
        val movies = movieRepository.loadMovies()
        view.showScreenMovies(movies)
    }

    override fun clickReservationButton(movieId: Int) {
        view.moveToReservation(movieId)
    }
}
