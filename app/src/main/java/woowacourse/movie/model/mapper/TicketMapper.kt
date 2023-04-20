package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.Ticket
import woowacourse.movie.model.TicketUI
import woowacourse.movie.model.mapper.seat.toSeatPosition
import woowacourse.movie.model.mapper.seat.toSeatPositionUI

fun Ticket.toTicketUI(): TicketUI = TicketUI(seatPosition.toSeatPositionUI())

fun TicketUI.toTicket(): Ticket = Ticket(seatPosition.toSeatPosition())
