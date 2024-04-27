package woowacourse.movie.presentation.main

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.MovieRepository

class MovieMainPresenter(
    private val movieChoiceContractView: MovieMainContract.View,
    private val movieRepository: MovieRepository = MovieRepositoryImpl(),
) :
    MovieMainContract.Presenter {
    override fun loadMovies() {
        movieChoiceContractView.onInitView(movieRepository.findAllMovies())
    }
}
