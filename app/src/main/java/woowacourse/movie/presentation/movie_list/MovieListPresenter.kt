package woowacourse.movie.presentation.movie_list

import woowacourse.movie.repository.movie.MovieRepository
import woowacourse.movie.repository.movie.PseudoMovieRepository
import woowacourse.movie.uimodel.movie.toMovieBrief

class MovieListPresenter(
    private val view: MovieListContract.View,
    movieRepository: MovieRepository = PseudoMovieRepository(),
) : MovieListContract.Presenter {
    private val movieBriefs =
        movieRepository.getMovies().map {
            it.toMovieBrief()
        }

    override fun loadMovies() {
        view.displayMovieBriefs(movieBriefs)
    }

    override fun selectMovie(movieId: Int) {
        view.navigateToMovieDetail(movieId)
    }
}
