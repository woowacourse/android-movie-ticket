package woowacourse.movie.view.seat

import woowacourse.movie.view.reservation.ticket.TicketUiModel

interface SeatSelectContract {
    interface View {
        fun navigateToCompleteScreen(ticket: TicketUiModel)
    }

    interface Presenter {
        fun completeReservation()
    }
}
