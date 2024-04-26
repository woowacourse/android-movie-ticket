package woowacourse.movie.seat

import woowacourse.movie.model.theater.Seat

interface TheaterSeatContract {
    interface View {
        fun updateSeatDisplay(seat: Seat)

        fun showConfirmationDialog()
        fun setSeatBackground(seatId: String, color: String) {
            TODO("Not yet implemented")
        }
    }

    interface Presenter {
        fun toggleSeatSelection(seatId: String)
        fun updateSeatBackground(seatId: String)
        fun calculateTotalPrice(): Int
        fun showConfirmationDialog()
    }
}
