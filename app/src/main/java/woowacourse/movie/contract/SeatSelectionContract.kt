package woowacourse.movie.contract

import woowacourse.movie.model.Theater
import woowacourse.movie.model.seat.Position

interface SeatSelectionContract {
    interface View{
        fun displayTheater(theater: Theater)
        fun displaySelectedSeat(position: Position)
        fun displayDeSelectedSeat(position: Position)
        fun activateConfirm()
        fun deActivateConfirm()
        fun displayTicketPrice(price: Int)
        fun displayConfirmDialog()
        fun navigateToPurchaseConfirmation()
    }
    interface Presenter{
        fun loadTheater()
        fun toggleSeatSelection(position: Position)
        fun askConfirm()
        fun purchase()
    }
}
