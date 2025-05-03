package woowacourse.movie.ui.seat

import woowacourse.movie.domain.ticket.MovieTicket

interface SeatsSelectionContract {
    interface View {
        fun showBookingConfirmDialog()

        fun showMovieTitle(movieTitle: String)

        fun showAmount(amount: Int)

        fun navigateToBookingSummary(movieTicket: MovieTicket)

        fun showSeatLimitToastMessage()

        fun changeSeatColor(
            row: Int,
            col: Int,
            isSelected: Boolean,
        )
    }

    interface Presenter {
        fun onConfirm()

        fun loadMovieTitle()

        fun loadAmount()

        fun updateAmount()

        fun onClickSeat(
            row: Int,
            col: Int,
        )
    }
}
