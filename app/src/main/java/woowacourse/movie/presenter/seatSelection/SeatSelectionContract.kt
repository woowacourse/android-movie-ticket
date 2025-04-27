package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.model.theater.TheaterUiModel

interface SeatSelectionContract {
    interface View {
        fun showMovieTitle(title: String)

        fun showTotalPrice(price: Int)

        fun showAlertDialog()

        fun showSelectionFinishedToast()

        fun showSelectionNotFinishedToast(required: Int)

        fun selectSeat(index: Int)

        fun deselectSeat(index: Int)

        fun goToReservationResult(
            ticket: TicketUiModel,
            seats: TheaterUiModel,
        )
    }

    interface Presenter {
        fun onViewCreated(ticketUiModel: TicketUiModel)

        fun onInstanceStateRestored(seats: TheaterUiModel)

        fun onSeatSelection(index: Int)

        fun onConfirmation()

        fun onAlertConfirmation()
    }
}
