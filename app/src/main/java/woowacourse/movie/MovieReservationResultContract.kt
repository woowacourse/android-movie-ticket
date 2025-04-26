package woowacourse.movie

import woowacourse.movie.view.model.TicketUiModel

interface MovieReservationResultContract {
    interface View {
        fun showReservationInfo(ticket: TicketUiModel)
    }

    interface Presenter {
        fun loadReservationInfo()
    }
}
