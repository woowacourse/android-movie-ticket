package woowacourse.movie.main

import woowacourse.movie.model.MovieRepository

class MainPresenter(
    private val view: MainContract.View,
    private val movieRepository: MovieRepository,
) : MainContract.Presenter {
    override fun onViewCreated() {
        view.displayMovies(movieRepository.getMovies())
    }

    override fun onMovieSelected(position: Int) {
        view.navigateToReservation(movieRepository.getMovie(position))
    }
}
