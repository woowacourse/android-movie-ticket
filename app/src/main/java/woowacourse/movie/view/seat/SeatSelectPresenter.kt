package woowacourse.movie.view.seat

import woowacourse.movie.view.reservation.ticket.TicketUiModel

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val ticket: TicketUiModel,
) : SeatSelectContract.Presenter {
    override fun completeReservation() {
        view.navigateToCompleteScreen(ticket)
    }
}
