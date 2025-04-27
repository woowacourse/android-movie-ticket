package woowacourse.movie

import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.LocalTime

interface MovieBookingSeat {
    interface View {
        fun showBookingStatusInfo()
        fun updateSeatCount(count: Int)
        fun showConfirmDialog(bookingStatus: BookingStatus)
        fun navigateToMovieBooked(bookingStatus: BookingStatus)
        fun showError(messageRes: Int)
    }

    interface Presenter {
        fun loadMovie(bookingStatus: BookingStatus)
        fun selectSeat()
        fun confirmBooking()
    }
}
