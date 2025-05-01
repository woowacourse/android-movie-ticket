package woowacourse.movie.ui.view.seat

import woowacourse.movie.domain.model.MovieTicket

interface SeatsSelectionContract {
    interface View {
        fun showBookingConfirmDialog()

        fun showMovieTitle(movieTitle: String)

        fun showAmount(amount: Int)

        fun navigateToBookingSummary(movieTicket: MovieTicket)

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
