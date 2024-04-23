package woowacourse.movie.detail.presenter

import woowacourse.movie.detail.presenter.contract.MovieDetailContract
import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.MovieReservationCount

class MovieDetailPresenter(
    private val detailContractView: MovieDetailContract.View,
    count: Int?,
) : MovieDetailContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()
    private var movieReservationCount =
        count?.let { MovieReservationCount(count) } ?: MovieReservationCount()

    override fun loadMovieDetail(id: Long) {
        val movieData = movieRepository.getMovieById(id)
        detailContractView.displayMovieDetail(movieData, movieReservationCount)
    }

    override fun plusReservationCount() {
        movieReservationCount = movieReservationCount.inc()
        detailContractView.updateCount(movieReservationCount.count)
    }

    override fun minusReservationCount() {
        movieReservationCount = movieReservationCount.dec()
        detailContractView.updateCount(movieReservationCount.count)
    }

    override fun reserveMovie(id: Long) {
        detailContractView.navigateToResultView(id, movieReservationCount.count)
    }
}
