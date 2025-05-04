package woowacourse.movie.feature.ticket

import woowacourse.movie.model.ticket.Ticket
import woowacourse.movie.model.ticket.TicketCount
import woowacourse.movie.model.ticket.TicketPrice
import woowacourse.movie.model.ticket.getOrDefault
import java.time.LocalDateTime

class TicketPresenter(
    private val view: TicketContract.TicketView,
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
            ticketCount = TicketCount.Companion.create(ticketCount).getOrDefault(),
            showtime = showtime,
        )
    }

    fun initTicketUi() {
        view.initTicketUI(ticket)
    }

    fun getTotalPrice(): TicketPrice = TicketPrice(ticketData.totalTicketPrice)
}
