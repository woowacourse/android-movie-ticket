package woowacourse.movie.ui.view.booking

import woowacourse.movie.ui.view.seat.SeatButtonState

interface SeatsSelectionContract {
    interface View {
        fun showBookingConfirmDialog()

        fun showMovieTitle(movieTitle: String)

        fun showAmount(amount: Int)

        fun navigateToBookingSummary()

        fun showSeatLimitToastMessage()
    }

    interface Presenter {
        fun onConfirm()

        fun loadMovieTitle()

        fun loadAmount()

        fun getSeatResult(seatName: String): SeatButtonState

        fun isSeatLimit(): Boolean

        fun updateAmount()
    }
}
