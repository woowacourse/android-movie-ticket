package woowacourse.movie.presenter.ticket

import woowacourse.movie.contract.ticket.TicketContract
import woowacourse.movie.domain.ticket.CancelTimePolicy
import woowacourse.movie.domain.ticket.DefaultCancelTimePolicy
import woowacourse.movie.domain.ticket.Ticket

class TicketPresenter(
    private val view: TicketContract.View,
    private val ticket: Ticket,
    private val cancelTimePolicy: CancelTimePolicy = DefaultCancelTimePolicy,
) : TicketContract.Presenter {
    override fun presentTitle() {
        view.setMovieTitle(ticket.title)
    }

    override fun presentShowtime() {
        view.setShowtime(ticket.showtime)
    }

    override fun presentCancelDescription() {
        view.setCancelDescription(cancelTimePolicy.cancelableMinutes)
    }
}
