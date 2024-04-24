package woowacourse.movie.presentation.detail

import woowacourse.movie.presentation.reservation.model.TicketModel

class TicketDetailPresenter(
    private val view: TicketDetailContract.View,
    private val ticketModel: TicketModel,
) : TicketDetailContract.Presenter {
    override fun loadTicket() {
        view.showTicket(ticketModel)
    }
}
