package woowacourse.movie.presenter

import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.repository.PseudoMovieRepository
import woowacourse.movie.repository.MovieRepository

class MovieListPresenter(
    private val view: MovieListContract.View,
    movieRepository: MovieRepository = PseudoMovieRepository(),
) : MovieListContract.Presenter {
    private val movies = movieRepository.getMovies()

    override fun loadMovies() {
        view.displayMovies(movies)
    }

    override fun selectMovie(movieId: Int) {
        view.navigateToMovieDetail(movieId)
    }
}
