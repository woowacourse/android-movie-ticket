package woowacourse.movie.presenter

import woowacourse.movie.model.MovieRepository

class MovieMainPresenter(private val movieChoiceContractView: MovieMainContract.View) :
    MovieMainContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepository()

    override fun loadMovies() {
        val movies = movieRepository.getAll()
        movieChoiceContractView.displayMovies(movies)
    }
}
