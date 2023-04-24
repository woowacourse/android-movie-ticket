package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.Reservation
import woowacourse.movie.model.ReservationUI

fun ReservationUI.toReservation(): Reservation =
    Reservation(movie.toMovie(), dateTime, ticketsUI.toTickets(), ticketCount.toTicketCount())

fun Reservation.toReservationUI(): ReservationUI =
    ReservationUI(movie.toMovieUI(), dateTime, tickets.toTicketsUI(), ticketCount.toTicketCountUI())
