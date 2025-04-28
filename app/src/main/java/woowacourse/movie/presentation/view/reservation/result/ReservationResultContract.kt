package woowacourse.movie.presentation.view.reservation.result

import woowacourse.movie.presentation.model.TicketBundleUiModel

interface ReservationResultContract {
    interface Presenter {
        fun fetchDate(ticketBundle: TicketBundleUiModel?)
    }

    interface View {
        fun showScreen(
            ticketBundle: TicketBundleUiModel,
            cancellationTime: Int,
        )

        fun notifyInvalidTickets()
    }
}
