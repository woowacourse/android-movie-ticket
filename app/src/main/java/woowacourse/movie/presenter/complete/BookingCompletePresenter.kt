package woowacourse.movie.presenter.complete

import woowacourse.movie.domain.model.ticket.Ticket

class BookingCompletePresenter(
    private val view: BookingCompleteContract.View,
    private val ticket: Ticket,
) : BookingCompleteContract.Presenter {
    override fun loadTicket() {
        view.showTicket(ticket)
    }
}
