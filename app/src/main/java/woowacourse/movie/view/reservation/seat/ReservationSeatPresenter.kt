package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.Ticket

class ReservationSeatPresenter(
    val view: ReservationSeatContract.View,
) : ReservationSeatContract.Present {
    override fun fetchData(ticket: Ticket?) {
        if (ticket == null) {
            view.handleInvalidTicket()
        } else {
            view.showReservationSeatScreen()
            view.setSeatTag()
            view.setSeatNumber()
        }
    }
}
