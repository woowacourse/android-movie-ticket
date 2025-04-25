package woowacourse.movie.view.reservation.seat

import android.content.Intent
import android.widget.TextView
import woowacourse.movie.model.ReservationInfo

interface SeatSelectContract {
    interface View {
        fun showErrorDialog()

        fun initReservationInfo(
            title: String,
            price: Int,
        )

        fun showSeatCountError(count: Int)

        fun updateSeatSelected(seatView: TextView)

        fun updateSeatDeselected(seatView: TextView)

        fun updateTotalPrice(totalPrice: Int)

        fun updateConfirmButtonState(isEnabled: Boolean)

        fun showReservationDialog(
            title: String,
            message: String,
        )
    }

    interface Presenter {
        fun fetchData(intent: Intent)

        fun onSeatClicked(seatView: TextView)

        fun createReservationInfo(): ReservationInfo

        fun onConfirmClicked(
            title: String,
            message: String,
        )
    }
}
