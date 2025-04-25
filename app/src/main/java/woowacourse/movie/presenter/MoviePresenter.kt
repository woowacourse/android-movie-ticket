package woowacourse.movie.presenter

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.ui.view.movies.MovieContract

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieRepository = MovieRepository(),
) : MovieContract.Presenter {
    override fun getMovies(): List<Movie> = repository.getAllMovies()

    override fun loadAllMovies() {
        val movies = getMovies()
        view.showAllMovies(movies)
    }
}
