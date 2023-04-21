package woowacourse.movie.model

import woowacourse.movie.domain.ticket.Ticket

fun Ticket.toPresentation(): TicketModel =
    TicketModel(title, playingDateTime, seats.size, seats.map { it.toPresentation() }, price.toPresentation())
