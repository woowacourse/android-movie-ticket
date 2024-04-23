package woowacourse.movie.presentation.ui.reservation

import woowacourse.movie.presentation.dto.MovieTicketUiModel

interface ReservationResultContract {
    interface View {
        fun showTicketData(movieTicket: MovieTicketUiModel)

        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadReservationDetails()
    }
}
