package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.view.model.SeatsUiModel
import woowacourse.movie.view.model.TicketUiModel

interface SeatSelectionContract {
    interface View {
        fun initializeSeats()

        fun selectSeat(
            row: Int,
            col: Int,
        )

        fun deselectSeat(
            row: Int,
            col: Int,
        )

        fun showMovieTitle(title: String)

        fun showTotalPrice(price: Int)

        fun showAlertDialog()

        fun confirmSelection(
            ticket: TicketUiModel,
            seats: SeatsUiModel,
        )
    }

    interface Presenter {
        fun loadReservationInfo()

        fun onSeatSelection(
            row: Int,
            col: Int,
        )

        fun onSelection()

        fun onSelectionConfirmation()
    }
}
