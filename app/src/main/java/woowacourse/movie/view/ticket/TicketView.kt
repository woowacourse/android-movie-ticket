package woowacourse.movie.view.ticket

import woowacourse.movie.model.ticket.Ticket
import woowacourse.movie.view.model.TicketData

interface TicketView {
    fun getTicketData(): TicketData

    fun initTicketUI(ticket: Ticket)
}
