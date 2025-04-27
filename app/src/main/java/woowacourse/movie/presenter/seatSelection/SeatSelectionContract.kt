package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.view.model.TheaterUiModel
import woowacourse.movie.view.model.TicketUiModel

interface SeatSelectionContract {
    interface View {
        fun initializeSeats()

        fun showMovieTitle(title: String)

        fun showTotalPrice(price: String)

        fun showAlertDialog()

        fun showSelectionFinishedToast()

        fun showSelectionNotFinishedToast(message: String)

        fun selectSeat(
            row: Int,
            col: Int,
        )

        fun deselectSeat(
            row: Int,
            col: Int,
        )

        fun goToReservationResult(
            ticket: TicketUiModel,
            seats: TheaterUiModel,
        )
    }

    interface Presenter {
        fun loadReservationInfo()

        fun onInstanceStateRestored(seats: TheaterUiModel)

        fun onSeatSelection(
            row: Int,
            col: Int,
        )

        fun onConfirmation()

        fun onAlertConfirmation()
    }
}
