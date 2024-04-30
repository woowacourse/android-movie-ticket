package woowacourse.movie.seat

import android.content.Intent
import woowacourse.movie.model.theater.Seat

interface TheaterSeatContract {
    interface View {
        fun updateSeatDisplay(seat: Seat)
        fun updateTotalPrice(price: Int)
        fun showConfirmationDialog(
            title: String,
            message: String,
            positiveLabel: String,
            onPositiveButtonClicked: () -> Unit,
            negativeLabel: String,
            onNegativeButtonClicked: () -> Unit
        )

        fun setSeatBackground(seatId: String, color: String)
        fun navigateToNextPage(intent: Intent)
    }

    interface Presenter {
        fun toggleSeatSelection(seatId: String)
        fun updateSeatBackground(seatId: String)
        fun calculateTotalPrice(): Int
        fun showConfirmationDialog(
            title: String,
            message: String,
            positiveLabel: String,
            onPositiveButtonClicked: () -> Unit,
            negativeLabel: String,
            onNegativeButtonClicked: () -> Unit
        )

        fun getSelectedSeatNumbers(): String
    }
}
