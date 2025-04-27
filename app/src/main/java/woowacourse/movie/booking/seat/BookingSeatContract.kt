package woowacourse.movie.booking.seat

import woowacourse.movie.booking.complete.BookingCompleteUiModel
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.Ticket

interface BookingSeatContract {
    interface Presenter {
        fun loadMovieTitle(ticket: Ticket)

        fun toggleSeatSelection(seat: Seat)

        fun onConfirmClicked()
    }

    interface View {
        fun showMovieTitle(title: String)

        fun updateSeatState(
            seat: Seat,
            isSelected: Boolean,
        )

        fun updateTotalPrice(price: Int)

        fun setConfirmEnabled(enabled: Boolean)

        fun showConfirmDialog()

        fun navigateToBookingComplete(bookingCompleteUiModel: BookingCompleteUiModel)
    }
}
