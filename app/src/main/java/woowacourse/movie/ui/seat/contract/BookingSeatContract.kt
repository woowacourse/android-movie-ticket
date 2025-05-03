package woowacourse.movie.ui.seat.contract

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.ui.seat.model.SeatState

interface BookingSeatContract {
    interface Presenter {
        fun restoreState(seatState: SeatState)

        fun refreshTotalPrice()

        fun refreshMovieTitle()

        fun selectSeat(seat: Seat)

        fun refreshConfirmButton()

        fun completeBookingSeat()
    }

    interface View {
        fun setTotalPrice(totalPrice: Int)

        fun setMovieTitle(movieTitle: String)

        fun toggleSeat(
            seatPosition: Seat,
            isOccupied: Boolean,
        )

        fun setConfirmButton(isEnabled: Boolean)

        fun startBookingCompleteActivity(
            movieTitle: String,
            headcount: Headcount,
            seats: Seats,
        )
    }
}
