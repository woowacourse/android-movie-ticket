package woowacourse.movie.domain.model.cinema.ticket

import woowacourse.movie.domain.model.cinema.PricePolicy
import woowacourse.movie.domain.model.cinema.screen.Seat
import woowacourse.movie.domain.model.reservation.ReservationInfo

class TicketMachine(
    private val policy: PricePolicy,
) {
    fun publishTickets(reservationInfo: ReservationInfo): TicketBundle {
        val tickets =
            reservationInfo.seats.map { seat ->
                reservationInfo.toTicket(seat)
            }

        return TicketBundle.bundleOf(tickets)
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
