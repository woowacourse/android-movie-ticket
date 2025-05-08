package woowacourse.movie.presenter.ticket

import woowacourse.movie.contract.ticket.TicketContract
import woowacourse.movie.domain.reservation.Seat
import woowacourse.movie.domain.ticket.CancelTimePolicy
import woowacourse.movie.domain.ticket.DefaultCancelTimePolicy
import woowacourse.movie.domain.ticket.Ticket

class TicketPresenter(
    private val view: TicketContract.View,
    private val ticket: Ticket,
    private val seats: Set<Seat>,
    private val cancelTimePolicy: CancelTimePolicy = DefaultCancelTimePolicy,
) : TicketContract.Presenter {
    override fun fetchTicket() {
        view.setCancelDescription(cancelTimePolicy.cancelableMinutes)
        view.setTicket(ticket, seats)
    }
}
