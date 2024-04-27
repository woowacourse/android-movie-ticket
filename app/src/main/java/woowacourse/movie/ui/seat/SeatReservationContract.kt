package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Seats

interface SeatReservationContract {
    interface View {
        fun showSeats(seat: Seats)

        fun showSeatReservationSuccess()

        fun showSeatReservationFail()
    }

    interface Presenter {
        fun loadSeats(screenId: Int)

        fun reserve(
            screenId: Int,
            seat: Seats,
        )
    }
}
