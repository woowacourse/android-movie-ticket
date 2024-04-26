package woowacourse.movie.main.presenter

import woowacourse.movie.data.MovieRepository.getAllMovies
import woowacourse.movie.main.presenter.contract.MovieMainContract

class MovieMainPresenter(private val movieMainContractView: MovieMainContract.View) :
    MovieMainContract.Presenter {
    override fun loadMovies() {
        val movies = getAllMovies()
        movieMainContractView.displayMovies(movies)
    }
}
