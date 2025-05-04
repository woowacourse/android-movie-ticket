package woowacourse.movie

import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.seat.Seat

interface MovieBookingSeat {
    interface View {
        fun showBookingStatusInfo()
        fun updateSeat(seat: Seat, isSelected: Boolean)
        fun updateButton()
        fun showTotalPrice(price: Int)
        fun showConfirmDialog(bookingStatus: BookingStatus)
        fun navigateToMovieBooked(bookingStatus: BookingStatus)
        fun showError(messageRes: Int)
    }

    interface Presenter {
        fun loadBookingStatus(bookingStatus: BookingStatus)
        fun selectSeat(seat: Seat)
        fun calculatePrice()
        fun confirmBooking()
    }
}
