package woowacourse.movie.domain.model

class TicketMachine {
    fun publishTickets(reservationInfo: ReservationInfo): List<Ticket> =
        reservationInfo.seats.map { seat ->
            Ticket(
                reservationInfo.title,
                reservationInfo.reservationDateTime,
                seat,
                seat.price(),
            )
        }

    fun canPublish(reservationInfo: ReservationInfo): Boolean = reservationInfo.seats.size == reservationInfo.reservationCount.value
}
