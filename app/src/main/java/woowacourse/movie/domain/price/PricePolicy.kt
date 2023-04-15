package woowacourse.movie.domain.price

interface PricePolicy {

    fun totalPriceCalculate(ticketPrice: TicketPrice, ticketCount: TicketCount): TicketPrice

    fun discountCalculate(price: TicketPrice): TicketPrice
}
