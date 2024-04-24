package woowacourse.movie.screen.main

import woowacourse.movie.data.MockMovieRepository

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun fetchMovieList() {
        val movies = MockMovieRepository.findAll()
        view.displayMovies(movies.map { it.toUiModel() })
    }

    override fun selectMovie(id: Long) {
        view.navigateToReservationScreen(id)
    }
}
