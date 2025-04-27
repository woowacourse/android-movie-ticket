package woowacourse.movie.view.reservation.result

import woowacourse.movie.domain.Ticket

class ReservationCompletePresenter(
    val view: ReservationCompleteContract.View,
) : ReservationCompleteContract.Presenter {
    override fun fetchData(ticket: Ticket?) {
        if (ticket == null) {
            view.handleInvalidTicket()
        } else {
            view.showTicketInfo(ticket)
        }
    }
}
