package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Position
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.TimeReservation

interface SeatReservationContract {
    interface View {
        fun showSeats(seats: Seats)

        fun showTimeReservations(timeReservation: TimeReservation)

        fun showTotalPrice(seats: Seats)

        fun activateReservation(boolean: Boolean)

        fun navigateToCompleteReservation(reservationId: Int)

        fun showSeatReservationFail(throwable: Throwable)

        fun showToast(e: Throwable)
    }

    interface Presenter {
        fun loadSeats(screenId: Int)

        fun loadTimeReservations(timeReservationId: Int)

        fun selectSeat(
            position: Position,
            seatView: android.view.View,
        )

        fun reserve(screenId: Int)
    }
}
