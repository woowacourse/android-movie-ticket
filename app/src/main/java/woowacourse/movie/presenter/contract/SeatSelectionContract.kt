package woowacourse.movie.presenter.contract

import android.widget.TextView
import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.theater.TheaterSize

interface SeatSelectionContract {
    interface View {
        fun initializeSeatTable(
            theaterSize: TheaterSize,
            rowClassInfo: Map<Int, SeatClass>,
        )

        fun selectSeat(
            textView: TextView,
            row: Int,
            column: Int,
            seatClass: SeatClass,
        )

        fun cancelSeat(
            textView: TextView,
            row: Int,
            column: Int,
            seatClass: SeatClass,
        )

        fun updateTotalPrice(totalPrice: Int)

        fun updateButtonStatus(isAvailable: Boolean)

        fun navigateToResultScreen(
            screeningId: Long,
            count: Int,
            seats: Array<String>,
            totalPrice: Int,
        )

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun initializeSeats(
            screeningId: Long,
            numOfTickets: Int,
            date: String?,
            time: String?,
        )

        fun makeReservation(
            screeningId: Long,
            count: Int,
        )

        fun addSeat(
            textView: TextView,
            row: Int,
            column: Int,
            seatClass: SeatClass,
        )

        fun removeSeat(
            textView: TextView,
            row: Int,
            column: Int,
            seatClass: SeatClass,
        )
    }
}
