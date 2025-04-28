package woowacourse.movie.view.result

import woowacourse.movie.view.reservation.model.TicketUiModel

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
    ticket: TicketUiModel,
) : ReservationResultContract.Presenter {
    private var ticket: TicketUiModel = ticket.copy()

    override fun loadReservationResultScreen() {
        view.showTicketInfo(ticket)
        view.showTotalPrice(ticket.totalPrice)
    }
}
