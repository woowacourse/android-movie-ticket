package woowacourse.movie.view.seat

import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.ticket.Ticket

interface SeatContract {
    interface View {
        fun showBookingInformation(title: String)

        fun showSeat(seat: Set<Seat>)

        fun showToast(peopleCount: Int)

        fun showPrice(price: Int)

        fun setConfirmButtonEnabled(clickable: Boolean)

        fun moveToBookingComplete(ticket: Ticket)
    }

    interface Presenter {
        fun loadBookingInfo()

        fun changeSeat(position: Seat)

        fun attemptConfirmBooking()
    }
}
