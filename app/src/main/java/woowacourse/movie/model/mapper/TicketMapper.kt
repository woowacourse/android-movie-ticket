package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.Ticket
import woowacourse.movie.model.TicketUI

fun Ticket.toTicketUI(): TicketUI = TicketUI(count)

fun TicketUI.toTicket(): Ticket = Ticket(count)
