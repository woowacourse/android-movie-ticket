package woowacourse.movie.view.complete

import woowacourse.movie.domain.model.ticket.Ticket

class BookingCompletePresenter(
    private val view: BookingCompleteContract.View,
    private val ticket: Ticket,
) : BookingCompleteContract.Presenter {
    init {
        loadTicket()
    }

    private fun loadTicket() {
        view.showTicket(ticket)
    }
}
