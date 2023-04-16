package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.TicketDomain
import woowacourse.movie.model.Ticket

fun TicketDomain.toPresentation(): Ticket = Ticket(count)

fun Ticket.toDomain(): TicketDomain = TicketDomain(count)
