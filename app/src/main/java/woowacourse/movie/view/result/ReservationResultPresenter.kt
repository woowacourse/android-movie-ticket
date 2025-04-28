package woowacourse.movie.view.result

import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.reservation.model.toDomain

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
    private val ticket: TicketUiModel,
) : ReservationResultContract.Presenter {
    override fun loadReservationResultScreen() {
        view.showTicketInfo(ticket)
        view.showTotalPrice(ticket.toDomain().totalPrice())
    }
}
