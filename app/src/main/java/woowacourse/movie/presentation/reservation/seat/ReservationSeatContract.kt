package woowacourse.movie.presentation.reservation.seat

import android.content.Intent
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket

interface ReservationSeatContract {
    interface View {
        fun setUpView(title: String)

        fun showSeatPrice(price: Int)

        fun updateSeatColorToWhite(row: Int, col: Int)

        fun updateSeatColorToYellow(row: Int, col: Int)

        fun ableClickCompleteText()

        fun unableClickCompleteText()

        fun navigateToResult(seats: Seats, ticket: Ticket)
    }

    interface Presenter {
        fun fetch(intent: Intent)

        fun onClickedSeat(row: Int, col: Int)

        fun checkSeatsCount()

        fun loadReservationInfo()
    }
}
