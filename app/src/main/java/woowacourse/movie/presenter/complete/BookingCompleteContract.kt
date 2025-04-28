package woowacourse.movie.presenter.complete

import woowacourse.movie.domain.model.ticket.Ticket

interface BookingCompleteContract {
    interface View {
        fun showTicket(ticket: Ticket)
    }

    interface Presenter {
        fun loadTicket()
    }
}
