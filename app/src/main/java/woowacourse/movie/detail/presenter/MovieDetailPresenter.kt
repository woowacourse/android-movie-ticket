package woowacourse.movie.detail.presenter

import woowacourse.movie.detail.presenter.contract.MovieDetailContract
import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.MovieReservationCount

class MovieDetailPresenter(
    private val movieDetailContractView: MovieDetailContract.View,
    count: Int?,
) : MovieDetailContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()
    private var movieReservationCount =
        count?.let { MovieReservationCount(count) } ?: MovieReservationCount()

    override fun loadMovieDetail(id: Long) {
        val movieData = movieRepository.getMovieById(id)
        movieDetailContractView.displayMovieDetail(movieData, movieReservationCount)
    }

    override fun plusReservationCount() {
        movieReservationCount = movieReservationCount.inc()
        movieDetailContractView.updateCount(movieReservationCount.count)
    }

    override fun minusReservationCount() {
        movieReservationCount = movieReservationCount.dec()
        movieDetailContractView.updateCount(movieReservationCount.count)
    }

    override fun reserveMovie(id: Long) {
        movieDetailContractView.navigateToResultView(id, movieReservationCount.count)
    }
}
