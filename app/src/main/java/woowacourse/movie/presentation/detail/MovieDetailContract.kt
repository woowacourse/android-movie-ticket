package woowacourse.movie.presentation.detail

import woowacourse.movie.presentation.reservation.model.TicketModel

interface MovieDetailContract {
    interface View {
        fun showTicket(ticketModel: TicketModel?)
    }

    interface Presenter {
        fun loadTicket(ticketModel: TicketModel?)
    }
}
