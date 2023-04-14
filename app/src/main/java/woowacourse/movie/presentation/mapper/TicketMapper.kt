package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.ticket.DomainTicket
import woowacourse.movie.presentation.model.Ticket

fun Ticket.toDomain(): DomainTicket =
    DomainTicket(count)

fun DomainTicket.toPresentation(): Ticket =
    Ticket(count)
