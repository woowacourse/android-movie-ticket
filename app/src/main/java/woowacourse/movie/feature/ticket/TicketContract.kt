package woowacourse.movie.feature.ticket

import woowacourse.movie.model.ticket.Ticket
import woowacourse.movie.view.model.TicketData

interface TicketContract {
    interface TicketView {
        fun getTicketData(): TicketData

        fun initTicketUI(ticket: Ticket)
    }
}
