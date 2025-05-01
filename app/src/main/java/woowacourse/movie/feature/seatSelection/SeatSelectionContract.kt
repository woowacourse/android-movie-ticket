package woowacourse.movie.feature.seatSelection

import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.model.seat.SeatsUiModel

interface SeatSelectionContract {
    interface View {
        fun showMovieTitle(title: String)

        fun showTotalPrice(price: Int)

        fun showAlertDialog()

        fun showSelectionFinishedToast()

        fun showSelectionNotFinishedToast(required: Int)

        fun toggleSeat(
            index: Int,
            isTaken: Boolean,
        )

        fun goToReservationResult(
            ticket: TicketUiModel,
            seats: SeatsUiModel,
        )
    }

    interface Presenter {
        fun loadReservationInfo(ticketUiModel: TicketUiModel)

        fun restoreReservationInfo(seats: SeatsUiModel)

        fun selectSeat(index: Int)

        fun finishSelection()

        fun confirmSelection()
    }
}
