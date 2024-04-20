package woowacourse.movie.presenter

import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.ReservationCount

class MovieDetailPresenter(
    private val detailContractView: MovieDetailContract.View,
    count: Int?,
) :
    MovieDetailContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()
    private var reservationCount = count?.let { ReservationCount(count) } ?: ReservationCount()

    override fun display(id: Long) {
        val movieData = movieRepository.getOneById(id)
        detailContractView.onInitView(movieData, reservationCount)
    }

    override fun plusReservationCount() {
        reservationCount.plus()
        detailContractView.onCountUpdate(reservationCount.count)
    }

    override fun minusReservationCount() {
        reservationCount.minus()
        detailContractView.onCountUpdate(reservationCount.count)
    }

    override fun reservation(id: Long) {
        detailContractView.onReservationComplete(id, reservationCount.count)
    }
}
