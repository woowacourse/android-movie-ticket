package woowacourse.movie.presenter.reservationComplete

import woowacourse.movie.model.MovieTicket

class ReservationCompletePresenter(
    private val view: ReservationCompleteContracts.View,
) : ReservationCompleteContracts.Presenter {
    override fun updateTicketData(movieTicket: MovieTicket) {
        view.showTitle(movieTicket.title)
        view.showTimestamp(movieTicket.movieDate, movieTicket.movieTime.value)
        view.showTicketCount(movieTicket.count)
        view.showPrice(movieTicket.price())
    }
}
