package woowacourse.movie.presenter

import woowacourse.movie.MovieBookingSeat
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.seat.Seat

class MovieBookingSeatPresenter(
    private val view: MovieBookingSeat.View
) : MovieBookingSeat.Presenter {
    private lateinit var bookingStatus: BookingStatus

    override fun loadBookingStatus(bookingStatus: BookingStatus) {
        this.bookingStatus = bookingStatus
        view.showBookingStatusInfo()
    }

    override fun selectSeat(seat: Seat) {
        if (seat !in bookingStatus.seat.seats) {
            bookingStatus.seat.add(seat)
            view.updateSeat(seat, true)
        } else {
            bookingStatus.seat.remove(seat)
            view.updateSeat(seat, false)
        }
    }

    override fun calculatePrice() {
        val totalPrice = bookingStatus.calculateTicketPrices()
        selectedAll()
        view.showTotalPrice(totalPrice)
    }

    private fun selectedAll() {
        if (bookingStatus.seat.isSelectedAll()) view.updateButton()
    }

    override fun confirmBooking() {
        view.showConfirmDialog(bookingStatus)
    }
}
