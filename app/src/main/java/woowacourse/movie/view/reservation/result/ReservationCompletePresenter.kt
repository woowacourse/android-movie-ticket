package woowacourse.movie.view.reservation.result

import woowacourse.movie.domain.Position
import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.Ticket

class ReservationCompletePresenter(
    val view: ReservationCompleteContract.View,
) : ReservationCompleteContract.Presenter {
    private lateinit var seats: Seats

    override fun fetchData(
        ticket: Ticket?,
        seats: Seats?,
    ) {
        if (ticket == null || seats == null) {
            view.handleInvalidTicket()
        } else {
            this.seats = seats
            view.showTicketInfo(ticket, seats)
            view.showSeatsInfo(seats.toSeatString())
            view.showTicketMoney(seats.reservationPrice())
        }
    }

    private fun Seats.toSeatString(): String {
        return this.all.joinToString(", ") { getSeatName(it.position) }
    }

    private fun getSeatName(position: Position): String {
        val columnChar = 'A' + position.row
        return "$columnChar${position.column + 1}"
    }
}
