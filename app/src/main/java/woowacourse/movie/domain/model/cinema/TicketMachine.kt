package woowacourse.movie.domain.model.cinema

import woowacourse.movie.domain.model.cinema.seat.Seat
import woowacourse.movie.domain.model.cinema.ticket.Ticket
import woowacourse.movie.domain.model.cinema.ticket.TicketBundle
import woowacourse.movie.domain.model.reservation.ReservationInfo

class TicketMachine(
    private val policy: PricePolicy,
) {
    fun publishTickets(reservationInfo: ReservationInfo): TicketBundle {
        val tickets = mutableListOf<Ticket>()
        reservationInfo.seats.forEach { seat ->
            tickets.add(reservationInfo.toTicket(seat))
        }

        return TicketBundle(tickets)
    }

    private fun ReservationInfo.toTicket(seat: Seat): Ticket =
        Ticket(
            this.title,
            this.reservationDateTime,
            seat,
            policy.calculatePrice(seat.type),
        )

    companion object {
        const val CANCELLATION_TIME = 15
    }
}
