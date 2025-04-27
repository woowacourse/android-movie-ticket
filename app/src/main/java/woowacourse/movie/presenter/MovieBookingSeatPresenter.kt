package woowacourse.movie.presenter

import woowacourse.movie.MovieBookingSeat
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie

class MovieBookingSeatPresenter(
    private val view: MovieBookingSeat.View
) : MovieBookingSeat.Presenter {
    private lateinit var bookingStatus: BookingStatus

    override fun loadBookingStatus(bookingStatus: BookingStatus) {
        this.bookingStatus = bookingStatus
        view.showBookingStatusInfo()
    }

    override fun selectSeat() {
        TODO("Not yet implemented")
    }

    override fun confirmBooking() {
        view.showConfirmDialog(bookingStatus)
    }
}
