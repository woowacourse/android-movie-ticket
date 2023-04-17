package woowacourse.movie.mapper

import woowacourse.movie.domain.TicketCount
import woowacourse.movie.dto.TicketCountDto

fun mapToTicket(ticketCountDto: TicketCountDto): TicketCount {
    return TicketCount(ticketCountDto.numberOfPeople)
}

fun mapToTicketDto(ticketCount: TicketCount): TicketCountDto {
    return TicketCountDto(ticketCount.numberOfPeople)
}
