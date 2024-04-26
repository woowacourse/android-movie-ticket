package woowacourse.movie.seat

import woowacourse.movie.model.theater.Seat

interface TheaterSeatContract {
    interface View {
        fun updateSeatDisplay(seat: Seat)
        fun updateTotalPrice(price: Int)
        fun showConfirmationDialog()
        fun setSeatBackground(seatId: String, color: String)
    }

    interface Presenter {
        fun toggleSeatSelection(seatId: String)
        fun updateSeatBackground(seatId: String)
        fun calculateTotalPrice(): Int
        fun showConfirmationDialog()
    }
}
