package woowacourse.movie.view.reservation

import woowacourse.movie.model.TicketCount

class ReservationPresenter(
    val view: ReservationContract.View,
    private var ticketCount: TicketCount,
) : ReservationContract.Presenter {
    override fun plusTicketCount() {
        ticketCount = ticketCount.plus(1)
        view.setTicketCount(ticketCount.value)
    }

    override fun minusTicketCount() {
        ticketCount = ticketCount.minus(1)
        view.setTicketCount(ticketCount.value)
    }

    override fun onReservationCompleted(
        title: String,
        message: String,
    ) {
        view.showReservationDialog(
            title,
            message,
        )
    }
}
