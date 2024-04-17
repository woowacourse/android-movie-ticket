package woowacourse.movie.presenter

import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.ReservationCount

class MovieDetailPresenter(private val detailContractView: MovieDetailContract.View) :
    MovieDetailContract.Presenter {
    private var reservationCount = ReservationCount()

    override fun plusReservationCount() {
        reservationCount.plus()
        detailContractView.onCountUpdate(reservationCount.count)
    }

    override fun minusReservationCount() {
        reservationCount.minus()
        detailContractView.onCountUpdate(reservationCount.count)
    }

    override fun reservation(movie: Movie) {
        detailContractView.onReservationComplete(MovieTicket(movie.title, movie.date, reservationCount.count))
    }
}
