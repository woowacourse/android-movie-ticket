package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.Ticket

class ReservationSeatPresenter(
    val view: ReservationSeatContract.View,
) : ReservationSeatContract.Present {
    private val seats = Seats(mutableListOf())

    override fun fetchData(ticket: Ticket?) {
        if (ticket == null) {
            view.handleInvalidTicket()
        } else {
            view.setSeatTag()
            view.setSeatInit()
            view.showMovieName(ticket.title)
            updateMoney()
        }
    }

    override fun updateMoney() {
        view.showTicketMoney(seats.reservationPrice())
    }
}
