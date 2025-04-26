package woowacourse.movie

import woowacourse.movie.domain.BookingStatus

interface MovieBooked {
    interface View {
        fun fetchBookingStatus()
        fun showBookedStatus(bookingStatus: BookingStatus)
    }

    interface Presenter {
        fun loadBookedStatus(bookingStatus: BookingStatus)
    }
}
