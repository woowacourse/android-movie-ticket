package woowacourse.movie.presenter

import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.ui.view.movies.MovieContract

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieRepository,
) : MovieContract.Presenter {
    override fun loadMovies() {
        val movies = repository.getAllMovies()
        view.showMovies(movies)
    }
}
