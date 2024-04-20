package woowacourse.movie.presenter

import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.MovieReservationCount

class MovieDetailPresenter(
    private val detailContractView: MovieDetailContract.View,
    count: Int?,
) :
    MovieDetailContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()
    private var movieReservationCount = count?.let { MovieReservationCount(count) } ?: MovieReservationCount()

    override fun display(id: Long) {
        val movieData = movieRepository.getOneById(id)
        detailContractView.onInitView(movieData, movieReservationCount)
    }

    override fun plusReservationCount() {
        movieReservationCount = movieReservationCount.inc()
        detailContractView.onCountUpdate(movieReservationCount.count)
    }

    override fun minusReservationCount() {
        movieReservationCount = movieReservationCount.dec()
        detailContractView.onCountUpdate(movieReservationCount.count)
    }

    override fun reservation(id: Long) {
        detailContractView.onReservationComplete(id, movieReservationCount.count)
    }
}
