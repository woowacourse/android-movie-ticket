package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.Position
import woowacourse.movie.domain.Seat
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
            view.setSeatClickListener()
            updateMoney()
        }
    }

    override fun addSeat(position: Position) {
        seats.addSeat(Seat(position))
    }

    override fun removeSeat(position: Position) {
        seats.removeSeat(Seat(position))
    }

    override fun updateMoney() {
        view.showTicketMoney(seats.reservationPrice())
    }
}
