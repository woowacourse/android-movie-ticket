package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats

interface SeatReservationContract {
    interface View {
        fun showSeats(seats: Seats)

        fun navigateToCompleteReservation(reservationId: Int)

        fun showSeatReservationFail(throwable: Throwable)
    }

    interface Presenter {
        fun loadSeats(screenId: Int)

        fun reserve(
            screen: Screen,
            seats: Seats,
        )
    }
}
