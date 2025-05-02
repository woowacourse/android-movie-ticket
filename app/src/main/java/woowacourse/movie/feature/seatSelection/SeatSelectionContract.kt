package woowacourse.movie.feature.seatSelection

import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.model.seat.SeatsUiModel

interface SeatSelectionContract {
    interface View {
        fun showReservationInfo(ticket: TicketUiModel)

        fun updateTotalPrice(price: Int)

        fun toggleSeat(
            index: Int,
            isTaken: Boolean,
        )

        fun showAlertDialog()

        fun showSelectionAlreadyFinishedToast()

        fun showSelectionNotFinishedToast(required: Int)

        fun goToReservationResult(
            ticket: TicketUiModel,
            seats: SeatsUiModel,
        )
    }

    interface Presenter {
        fun loadReservationInfo(ticket: TicketUiModel)

        fun restoreReservationInfo(seats: SeatsUiModel)

        fun selectSeat(index: Int)

        fun finishSelection()

        fun confirmSelection()
    }
}
