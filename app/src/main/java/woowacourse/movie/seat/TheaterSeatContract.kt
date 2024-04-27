package woowacourse.movie.seat

import android.content.Intent
import woowacourse.movie.model.theater.Seat
import woowacourse.movie.model.theater.Theater

interface TheaterSeatContract {
    interface View {
        fun updateSeatDisplay(seat: Seat)
        fun updateTotalPrice(price: Int)
        fun showConfirmationDialog()
        fun setSeatBackground(seatId: String, color: String)
        fun navigateToNextPage(intent: Intent)
    }

    interface Presenter {
        fun toggleSeatSelection(seatId: String)
        fun updateSeatBackground(seatId: String)
        fun calculateTotalPrice(): Int
        fun showConfirmationDialog()
        fun getSelectedSeatNumbers(): String
    }
}
