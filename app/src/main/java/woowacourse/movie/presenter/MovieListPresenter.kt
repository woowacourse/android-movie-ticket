package woowacourse.movie.presenter

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.presenter.contract.MovieListContract

class MovieListPresenter(private val movieListContractView: MovieListContract.View) : MovieListContract.Presenter {
    private val movieRepository = MovieRepository()

    override fun loadMovies() {
        val movies = movieRepository.getAllMovies()
        movieListContractView.displayMovies(movies)
    }
}
