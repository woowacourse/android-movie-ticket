package woowacourse.movie.feature.movieReservationResult

import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.model.seat.SeatsUiModel

interface MovieReservationResultContract {
    interface View {
        fun showReservationInfo(
            ticket: TicketUiModel,
            selectedSeats: String,
        )

        fun updateTotalPrice(price: Int)
    }

    interface Presenter {
        fun initializeReservationInfo(
            ticket: TicketUiModel,
            seats: SeatsUiModel,
        )
    }
}
