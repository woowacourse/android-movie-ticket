package woowacourse.movie.main.presenter

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.main.presenter.contract.MovieMainContract

class MovieMainPresenter(private val movieMainContractView: MovieMainContract.View) :
    MovieMainContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepository()

    override fun loadMovies() {
        val movies = movieRepository.getAllMovies()
        movieMainContractView.displayMovies(movies)
    }
}
