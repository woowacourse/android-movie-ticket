package woowacourse.movie.feature.main

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.feature.main.ui.toUiModel

class MainPresenter(private val view: MainContract.View, private val repository: MovieRepository) :
    MainContract.Presenter {
    override fun fetchMovieList() {
        val movies = repository.findAll()
        view.displayMovies(movies.map { it.toUiModel() })
    }

    override fun selectMovie(id: Long) {
        view.navigateToReservationScreen(id)
    }
}
