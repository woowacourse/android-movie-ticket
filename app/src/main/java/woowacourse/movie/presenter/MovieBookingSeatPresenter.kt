package woowacourse.movie.presenter

import woowacourse.movie.MovieBookingSeat
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.seat.Seat

class MovieBookingSeatPresenter(
    private val view: MovieBookingSeat.View
) : MovieBookingSeat.Presenter {
    private lateinit var bookingStatus: BookingStatus
    private val seats: MutableList<Seat> = mutableListOf()

    override fun loadBookingStatus(bookingStatus: BookingStatus) {
        this.bookingStatus = bookingStatus
        view.showBookingStatusInfo()
    }

    override fun selectSeat(seat: Seat) {
        if (seat !in seats) {
            seats.add(seat)
            view.updateSeat(seat, true)
        } else {
            seats.remove(seat)
            view.updateSeat(seat, false)
        }

    }

    override fun calculatePrice() {
        val totalPrice = seats.size
        view.showTotalPrice(totalPrice)
    }

    override fun confirmBooking() {
        view.showConfirmDialog(bookingStatus)
    }
}
