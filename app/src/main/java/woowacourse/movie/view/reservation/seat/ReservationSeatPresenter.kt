package woowacourse.movie.view.reservation.seat

import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movieseat.Position
import woowacourse.movie.domain.movieseat.Seat
import woowacourse.movie.domain.movieseat.Seats

class ReservationSeatPresenter(
    val view: ReservationSeatContract.View,
) : ReservationSeatContract.Present {
    private val seats = Seats(mutableListOf())
    private lateinit var ticket: Ticket

    override fun fetchData(ticket: Ticket?) {
        if (ticket == null) {
            view.handleInvalidTicket()
        } else {
            this.ticket = ticket
            view.setSeatTag()
            view.setSeatInit()
            view.showMovieName(ticket.title)
            view.setSeatClickListener()
            view.setReservationButton {
                view.showReservationDialog(ticket, seats)
            }
            updateMoney()
        }
    }

    override fun selectSeat(position: Position) {
        if (seats.selectedLimit(ticket.personnel).not()) {
            seats.addSeat(Seat(position))
            view.selectSeatView(position)
            updateMoney()
            canSelectedButton()
        }
    }

    override fun deselectSeat(position: Position) {
        seats.removeSeat(Seat(position))
        view.deselectSeatView(position)
        updateMoney()
        canSelectedButton()
    }

    override fun updateMoney() {
        view.showTicketMoney(seats.reservationPrice())
    }

    private fun canSelectedButton() {
        if (seats.canSelect(ticket.personnel)) {
            view.selectableButton()
        } else {
            view.deSelectableButton()
        }
    }
}
