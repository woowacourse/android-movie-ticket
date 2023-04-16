package woowacourse.movie.mapper

import woowacourse.movie.domain.TicketPrice
import woowacourse.movie.dto.TicketPriceDto

fun mapToTicketPrice(ticketPriceDto: TicketPriceDto): TicketPrice {
    return TicketPrice(ticketPriceDto.price)
}

fun mapToTicketPriceDto(ticketPrice: TicketPrice): TicketPriceDto {
    return TicketPriceDto(ticketPrice.price)
}
