package woowacourse.movie.seat

import android.widget.TextView
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Seats

interface SeatSelectContract {
    interface View {
        fun showReservationInfo(
            movieTitle: String,
            totalPrice: Int,
        )

        fun showTotalPrice(totalPrice: Int)

        fun showReservationCheck(isAvailable: Boolean)

        fun changeSeatColor(
            isSelected: Boolean,
            textView: TextView,
        )

        fun showConfirmDialog()

        fun moveToReservationFinished(
            movieId: Int,
            ticket: Seats,
            seats: String,
            totalPrice: Int,
            reservationSchedule: ReservationSchedule,
        )
    }

    interface Presenter {
        fun loadSavedData()

        fun loadMovieTitle()

        fun loadReservationInformation()

        fun selectSeat(
            seat: String,
            isSelected: Boolean,
            textView: TextView,
        )

        fun confirm()
    }
}
