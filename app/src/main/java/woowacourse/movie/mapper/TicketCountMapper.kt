package woowacourse.movie.mapper

import domain.TicketCount
import woowacourse.movie.dto.TicketCountDto

fun TicketCountDto.mapToTicketCount(): TicketCount {
    return TicketCount(this.numberOfPeople)
}

fun TicketCount.mapToTicketCountDto(): TicketCountDto {
    return TicketCountDto(this.numberOfPeople)
}
