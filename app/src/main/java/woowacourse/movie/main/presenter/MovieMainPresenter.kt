package woowacourse.movie.main.presenter

import woowacourse.movie.model.MovieAdapter
import woowacourse.movie.model.MovieRepository

class MovieMainPresenter(private val movieChoiceContractView: MovieMainContract.View) :
    MovieMainContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepository()
    private lateinit var movieAdapter: MovieAdapter

    override fun loadMovies() {
        movieAdapter = MovieAdapter(movieChoiceContractView::onMovieItemClick, movieRepository.getAll())
        movieChoiceContractView.onInitAdapter(movieAdapter)
    }
}
