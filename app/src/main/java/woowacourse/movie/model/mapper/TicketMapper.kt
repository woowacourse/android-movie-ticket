package woowacourse.movie.model.mapper

import movie.domain.Ticket
import woowacourse.movie.model.TicketState

fun TicketState.toDomain(): Ticket = Ticket(count.toDomain(), movieData.toDomain(), screeningDateTime.toDomain(), price, seatSelection.toDomain())

fun Ticket.toPresentation(): TicketState = TicketState(count.toPresentation(), movieData.toPresentation(), screeningDateTime.toPresentation(), price, seatSelection.toPresentation())
