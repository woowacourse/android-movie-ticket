package woowacourse.movie.feature.ticket

import woowacourse.movie.model.ticket.Ticket

interface TicketContract {
    interface TicketView {
        fun getTicketData(): TicketData

        fun initTicketUI(ticket: Ticket)
    }
}
