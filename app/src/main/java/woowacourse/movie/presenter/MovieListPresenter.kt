package woowacourse.movie.presenter

import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.repository.PseudoMovieRepository
import woowacourse.movie.uimodel.toBrief

class MovieListPresenter(
    private val view: MovieListContract.View,
    movieRepository: MovieRepository = PseudoMovieRepository(),
) : MovieListContract.Presenter {
    private val movieBriefs =
        movieRepository.getMovies().map {
            it.toBrief()
        }

    override fun loadMovies() {
        view.displayMovieBriefs(movieBriefs)
    }

    override fun selectMovie(movieId: Int) {
        view.navigateToMovieDetail(movieId)
    }
}
