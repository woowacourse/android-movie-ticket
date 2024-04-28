package woowacourse.movie.ui.seatselection

import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats

interface SeatSelectionContract {
    interface View {
        fun initViews(title: String)
        fun updateSeatSelection(seat: Seat, occupied: Boolean)
        fun updatePrice(price: Int)
        fun updateConfirmButton(isEnabled: Boolean)
        fun navigateToResultActivity(ticket: Ticket, title: String)
    }

    interface Presenter {
        fun initialize(ticket: Ticket, title: String)
        fun onSeatClicked(seat: Seat)
        fun onConfirmClicked()

        fun isCountSufficient(): Boolean
        fun updateConfirmButtonState()
    }
}
