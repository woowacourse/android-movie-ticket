package woowacourse.movie.mapper

import domain.TicketPrice
import woowacourse.movie.dto.TicketPriceDto

fun TicketPriceDto.mapToTicketPrice(): TicketPrice {
    return TicketPrice(this.price)
}

fun TicketPrice.mapToTicketPriceDto(): TicketPriceDto {
    return TicketPriceDto(this.price)
}
