package woowacourse.movie.view.reservation

import woowacourse.movie.view.reservation.model.TicketUiModel

interface MovieReservationCompleteContract {
    interface View {
        fun showTicketInfo(ticket: TicketUiModel)

        fun showTotalPrice(totalPrice: Int)
    }

    interface Presenter {
        fun loadMovieReservationCompleteScreen()
    }
}
