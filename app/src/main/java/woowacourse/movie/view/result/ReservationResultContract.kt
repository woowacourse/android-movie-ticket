package woowacourse.movie.view.result

import woowacourse.movie.view.reservation.model.TicketUiModel

interface ReservationResultContract {
    interface View {
        fun showTicketInfo(ticket: TicketUiModel)

        fun showTotalPrice(totalPrice: Int)
    }

    interface Presenter {
        fun loadReservationResultScreen()
    }
}
