package woowacourse.movie.mapper

import woowacourse.movie.domain.Ticket
import woowacourse.movie.dto.TicketDto

fun mapToTicket(ticketDto: TicketDto): Ticket {
    return Ticket(ticketDto.numberOfPeople)
}

fun mapToTicketDto(ticket: Ticket): TicketDto {
    return TicketDto(ticket.numberOfPeople)
}
