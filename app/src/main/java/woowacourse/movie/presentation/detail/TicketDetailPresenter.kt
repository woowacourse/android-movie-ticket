package woowacourse.movie.presentation.detail

import woowacourse.movie.presentation.reservation.model.TicketModel

class TicketDetailPresenter(
    private val view: TicketDetailContract.View,
) : TicketDetailContract.Presenter {
    override fun loadTicket(ticketModel: TicketModel?) {
        view.showTicket(ticketModel)
    }
}
