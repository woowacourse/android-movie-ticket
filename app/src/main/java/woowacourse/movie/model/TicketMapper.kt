package woowacourse.movie.model

import woowacourse.movie.domain.ticket.Ticket

fun Ticket.toPresentation(): TicketModel = TicketModel(title, playingDate, playingTime, count, price.price)
