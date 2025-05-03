package woowacourse.movie.ui.movies

import woowacourse.movie.domain.movies.MovieRepository

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieRepository = MovieRepository(),
) : MovieContract.Presenter {
    override fun loadAllMovies() {
        val movies = repository.getAllMovies()
        view.showAllMovies(movies)
    }
}
