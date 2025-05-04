package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.domain.model.Seat

interface SeatSelectionContract {
    interface View {
        fun showSeats(seats: List<Seat>)

        fun updateSeatSelection(
            seat: Seat,
            isSelected: Boolean,
        )

        fun showTotalPrice(price: Int)

        fun showMovieTitle(title: String)

        fun enableConfirmButton(enabled: Boolean)

        fun showError(message: String)

        fun showReservationDialog()

        fun navigateToResult(reservationInfo: ReservationInfo)
    }

    interface Presenter {
        fun loadSeats(reservationInfo: ReservationInfo?)

        fun selectSeat(seat: Seat)

        fun showConfirmButton()

        fun completeReservation()
    }
}
