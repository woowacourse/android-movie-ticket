package woowacourse.movie.presenter

import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.ui.view.movies.MovieContract

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieRepository,
) : MovieContract.Presenter {
    override fun loadAllMovies() {
        val movies = repository.getAllMovies()
        view.showAllMovies(movies)
    }
}
