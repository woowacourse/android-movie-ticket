package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.TimeReservation

interface SeatReservationContract {
    interface View {
        fun showSeats(seats: Seats)

        fun showTimeReservations(timeReservation: TimeReservation)

        fun navigateToCompleteReservation(reservationId: Int)

        fun showSeatReservationFail(throwable: Throwable)
    }

    interface Presenter {
        fun loadSeats(screenId: Int)

        fun loadTimeReservations(timeReservationId: Int)

        fun selectSeat(seat: Seat)

        fun reserve(
            screen: Screen,
            seats: Seats,
        )
    }
}
