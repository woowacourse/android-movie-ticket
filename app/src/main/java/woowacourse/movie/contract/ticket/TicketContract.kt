package woowacourse.movie.contract.ticket

import woowacourse.movie.domain.reservation.Seat
import woowacourse.movie.domain.ticket.Ticket

interface TicketContract {
    interface Presenter {
        fun fetchTicket()
    }

    interface View {
        fun setCancelDescription(minutes: Int)

        fun setTicket(
            ticket: Ticket,
            seats: Set<Seat>,
        )
    }
}
