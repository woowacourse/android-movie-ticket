package woowacourse.movie.presentation.view.reservation.result

import woowacourse.movie.domain.model.cinema.ticket.TicketMachine
import woowacourse.movie.presentation.model.TicketBundleUiModel

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
) : ReservationResultContract.Presenter {
    override fun fetchDate(ticketBundle: TicketBundleUiModel?) {
        ticketBundle?.let { bundle ->
            view.setScreen(bundle, TicketMachine.CANCELLATION_TIME)
            return
        }

        view.notifyInvalidTickets()
    }
}
