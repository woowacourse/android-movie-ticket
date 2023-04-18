package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.Reservation
import woowacourse.movie.model.ReservationUI

fun ReservationUI.toReservation(): Reservation =
    Reservation(movie.toMovie(), dateTime, ticket.toTicket(), totalPrice)

fun Reservation.toReservationUI(): ReservationUI =
    ReservationUI(movie.toMovieUI(), dateTime, ticket.toTicketUI(), totalPrice)
