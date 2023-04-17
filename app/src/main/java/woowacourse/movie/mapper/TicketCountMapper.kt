package woowacourse.movie.mapper

import woowacourse.movie.domain.TicketCount
import woowacourse.movie.dto.TicketCountDto

fun TicketCountDto.mapToTicket(): TicketCount {
    return TicketCount(this.numberOfPeople)
}

fun TicketCount.mapToTicketDto(): TicketCountDto {
    return TicketCountDto(this.numberOfPeople)
}
