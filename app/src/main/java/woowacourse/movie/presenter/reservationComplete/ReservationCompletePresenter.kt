package woowacourse.movie.presenter.reservationComplete

import woowacourse.movie.model.ticket.MovieTicket

class ReservationCompletePresenter(
    private val view: ReservationCompleteContracts.View,
) : ReservationCompleteContracts.Presenter {
    override fun updateTicketData(movieTicket: MovieTicket) {
        view.showTicket(movieTicket)
    }
}
