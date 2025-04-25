package woowacourse.movie.presenter

import woowacourse.movie.model.ticket.Ticket
import woowacourse.movie.model.ticket.TicketCount
import woowacourse.movie.model.ticket.getOrDefault
import woowacourse.movie.view.model.TicketData
import woowacourse.movie.view.ticket.TicketView
import java.time.LocalDateTime

class TicketPresenter(
    private val view: TicketView,
) {
    private val ticketData: TicketData by lazy {
        view.getTicketData()
    }
    private val ticket: Ticket by lazy {
        val screening = ticketData.screeningData.toScreening()
        val ticketCount = ticketData.ticketCount
        val showtime: LocalDateTime = ticketData.showtime
        Ticket(
            screening = screening,
            ticketCount = TicketCount.create(ticketCount).getOrDefault(),
            showtime = showtime,
        )
    }

    fun initTicketUi() {
        view.initTicketUI(ticket)
    }
}
