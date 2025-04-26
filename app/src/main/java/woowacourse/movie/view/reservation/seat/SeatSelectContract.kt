package woowacourse.movie.view.reservation.seat

import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.ReservationInfo

interface SeatSelectContract {
    interface View {
        fun showErrorDialog()

        fun initReservationInfo(
            title: String,
            price: Int,
        )

        fun showSeatCountError(count: Int)

        fun updateSeatSelected(seatId: String)

        fun updateSeatDeselected(seatId: String)

        fun updateTotalPrice(totalPrice: Int)

        fun updateConfirmButtonState(isEnabled: Boolean)

        fun showReservationDialog(
            title: String,
            message: String,
        )

        fun navigateToComplete(reservationInfo: ReservationInfo)
    }

    interface Presenter {
        fun fetchData(getMovieTicket: () -> MovieTicket?)

        fun onSeatClicked(seatId: String)

        fun createReservationInfo(onCreated: (ReservationInfo) -> Unit)

        fun onConfirmClicked(
            title: String,
            message: String,
        )
    }
}
