package woowacourse.movie.view.reservation

import woowacourse.movie.view.reservation.ticket.TicketUiModel

interface MovieReservationCompleteContract {
    interface View {
        fun showTicketInfo(ticket: TicketUiModel)

        fun showTotalPrice(totalPrice: Int)
    }

    interface Presenter {
        fun loadMovieReservationCompleteScreen()
    }
}
