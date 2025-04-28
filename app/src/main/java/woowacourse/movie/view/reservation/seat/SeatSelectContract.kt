package woowacourse.movie.view.reservation.seat

import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.ReservationInfo

interface SeatSelectContract {
    interface View {
        fun showErrorDialog()

        fun showReservationInfo(
            title: String,
            price: Int,
        )

        fun showSeatCountError(count: Int)

        fun showSelectedSeat(seatId: String)

        fun showDeselectedSeat(seatId: String)

        fun showTotalPrice(totalPrice: Int)

        fun updateConfirmButtonEnabled(isEnabled: Boolean)

        fun showReservationDialog(
            title: String,
            message: String,
        )

        fun navigateToComplete(reservationInfo: ReservationInfo)
    }

    interface Presenter {
        fun fetchData(getMovieTicket: () -> MovieTicket?)

        fun seatSelect(seatId: String)

        fun createReservationInfo(onCreated: (ReservationInfo) -> Unit)

        fun onConfirmClicked(
            title: String,
            message: String,
        )
    }
}
