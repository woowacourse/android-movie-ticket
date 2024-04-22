package woowacourse.movie.main.presenter

import woowacourse.movie.main.model.MovieRepository
import woowacourse.movie.main.presenter.contract.MovieMainContract

class MovieMainPresenter(private val movieChoiceContractView: MovieMainContract.View) :
    MovieMainContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepository()

    override fun loadMovies() {
        val movies = movieRepository.getAllMovies()
        movieChoiceContractView.displayMovies(movies)
    }
}
