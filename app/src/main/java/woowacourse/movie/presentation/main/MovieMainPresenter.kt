package woowacourse.movie.presentation.main

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.presentation.adapter.MovieAdapter

class MovieMainPresenter(private val movieChoiceContractView: MovieMainContract.View) :
    MovieMainContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepositoryImpl()

    private lateinit var movieAdapter: MovieAdapter

    override fun loadMovies() {
        movieAdapter = MovieAdapter(movieChoiceContractView::onMovieItemClick, movieRepository.findAll())
        movieChoiceContractView.onInitAdapter(movieAdapter)
    }
}
