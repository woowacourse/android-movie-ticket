package woowacourse.movie.main.presenter

import woowacourse.movie.main.presenter.contract.MovieMainContract
import woowacourse.movie.model.MovieRepository

class MovieMainPresenter(private val movieChoiceContractView: MovieMainContract.View) :
    MovieMainContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepository()

    override fun loadMovies() {
        val movies = movieRepository.getAllMovies()
        movieChoiceContractView.displayMovies(movies)
    }
}
