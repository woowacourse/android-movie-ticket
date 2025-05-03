package woowacourse.movie.ui.seat.contract

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats

interface BookingSeatContract {
    interface Presenter {
        fun refreshTotalPrice()

        fun refreshMovieTitle()

        fun restoreHeadcount(headcount: Headcount)

        fun restoreMovieTitle(movieTitle: String)

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
