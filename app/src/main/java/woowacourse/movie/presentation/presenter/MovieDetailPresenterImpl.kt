package woowacourse.movie.presentation.presenter

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.presentation.contract.MovieDetailContract

class MovieDetailPresenterImpl(
    private val view: MovieDetailContract.View,
    title: String,
    screeningDate: String,
    reservationCount: Int = MovieTicket.MIN_RESERVATION_COUNT,
) : MovieDetailContract.Presenter {
    override val movieTicket: MovieTicket = MovieTicket(title, screeningDate, reservationCount)

    override fun minusReservationCount() {
        movieTicket.minusCount()
        view.showReservationCount(movieTicket.reservationCount)
    }

    override fun plusReservationCount() {
        movieTicket.plusCount()
        view.showReservationCount(movieTicket.reservationCount)
    }
}
