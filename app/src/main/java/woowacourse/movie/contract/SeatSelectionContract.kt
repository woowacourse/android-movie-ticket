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
    }
    interface Presenter{
        fun loadTheater()
        fun toggleSeatSelection(position: Position)
    }
}
