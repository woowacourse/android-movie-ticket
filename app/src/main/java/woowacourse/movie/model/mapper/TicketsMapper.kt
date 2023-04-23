package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.Tickets
import woowacourse.movie.model.TicketsUI

fun Tickets.toTicketsUI(): TicketsUI =
    TicketsUI(tickets.map { it.toTicketUI() }.toSet(), reservation.toReservationUI())

fun TicketsUI.toTickets(): Tickets =
    Tickets(tickets.map { it.toTicket() }.toSet(), reservation.toReservation())
