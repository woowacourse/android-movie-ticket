package woowacourse.movie.presentation.detail

import woowacourse.movie.presentation.model.TicketModel

interface TicketDetailContract {
    interface View {
        fun showTicket(ticketModel: TicketModel)
    }

    interface Presenter {
        fun loadTicket()
    }
}
