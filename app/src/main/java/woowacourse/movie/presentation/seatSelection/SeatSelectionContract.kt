package woowacourse.movie.presentation.seatSelection

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Ticket

interface SeatSelectionContract {
    interface View {
        fun initializeSeats(
            seats: List<Seat>,
            selectedSeats: List<Int>,
        )

        fun initializeTicketInfo(movie: Movie)

        fun updateSelectedSeatUI(index: Int)

        fun updateUnSelectedSeatUI(index: Int)

        fun updateTotalPrice(price: Int)

        fun showToastMessage(message: String?)

        fun setButtonEnabledState(isEnabled: Boolean)

        fun navigate(ticket: Ticket)
    }

    interface Presenter {
        fun initializeViewData()

        fun updateSeatSelection(index: Int)

        fun navigate()
    }
}
