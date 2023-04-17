package woowacourse.movie.mapper

import woowacourse.movie.domain.TicketPrice
import woowacourse.movie.dto.TicketPriceDto

fun TicketPriceDto.mapToTicketPrice(): TicketPrice {
    return TicketPrice(this.price)
}

fun TicketPrice.mapToTicketPriceDto(): TicketPriceDto {
    return TicketPriceDto(this.price)
}
