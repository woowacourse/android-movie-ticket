package woowacourse.movie.view.reservation.result

import woowacourse.movie.domain.Ticket

class ReservationCompleteContract {
    interface Presenter {
        fun fetchData(ticket: Ticket?)
    }

    interface View {
        fun handleInvalidTicket()

        fun showTicketInfo(ticket: Ticket)
    }
}
