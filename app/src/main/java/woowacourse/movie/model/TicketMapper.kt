package woowacourse.movie.model

import woowacourse.movie.domain.ticket.Ticket

fun Ticket.toPresentation(): TicketModel {
    val sortedSeats = seats.sortedWith(compareBy({ it.row }, { it.col }))
    return TicketModel(title, playingDateTime, seats.size, sortedSeats.map { it.toPresentation() }, price.toPresentation())
}
