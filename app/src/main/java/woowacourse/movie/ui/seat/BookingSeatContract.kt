package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats

interface BookingSeatContract {
    interface Presenter {
        fun fetchHeadcount(): Headcount

        fun fetchMovieTitle(): String

        fun updateTotalPrice()

        fun updateMovieTitle()

        fun updateSeat(seatTag: String)

        fun updateConfirmButton()

        fun completeBookingSeat()
    }

    interface View {
        fun getHeadcount(): Headcount?

        fun getMovieTitle(): String?

        fun setTotalPriceTextView(totalPrice: Int)

        fun setMovieTitleTextView(movieTitle: String)

        fun toggleSeat(
            seatPosition: Seat,
            isOccupied: Boolean,
        )

        fun setConfirmButton(isEnabled: Boolean)

        fun moveToBookingCompleteActivity(
            movieTitle: String,
            headcount: Headcount,
            seats: Seats,
        )
    }
}
