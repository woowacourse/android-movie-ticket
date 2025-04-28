package woowacourse.movie.presenter.seat

import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.domain.model.ticket.Ticket
import woowacourse.movie.view.seat.model.coord.Coordination

class SeatPresenter(
    private val view: SeatContract.View,
    private val seats: Seats,
    private val booking: Booking,
) : SeatContract.Presenter {
    init {
        loadBookingInfo()
    }

    private val limit = booking.count.value

    override fun loadBookingInfo() {
        view.showBookingInformation(booking.title)
        view.showPrice(0)
    }

    override fun changeSeat(position: Coordination) {
        val newSeat = Seat(x = position.x.value, y = position.y.value)
        val changed = seats.toggleSeat(newSeat, limit)

        if (changed) {
            view.showSeat(seats.item)
            view.showPrice(seats.bookingPrice())
            updateConfirmButtonState(limit)
        } else {
            view.showToast(limit)
        }
    }

    override fun attemptConfirmBooking() {
        if (seats.isNotSelectDone(limit)) {
            view.showToast(limit)
            return
        }

        val ticket = Ticket.initialize(booking, seats.item, seats.bookingPrice())
        view.moveToBookingComplete(ticket)
    }

    private fun updateConfirmButtonState(peopleCount: Int) {
        val isEnabled = seats.item.size == peopleCount
        view.setConfirmButtonEnabled(isEnabled)
    }
}
