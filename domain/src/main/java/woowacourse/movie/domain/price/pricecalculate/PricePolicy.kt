package woowacourse.movie.domain.price.pricecalculate

import woowacourse.movie.domain.price.TicketCount
import woowacourse.movie.domain.price.TicketPrice

interface PricePolicy {

    fun totalPriceCalculate(ticketPrice: TicketPrice, ticketCount: TicketCount): TicketPrice

    fun discountCalculate(price: TicketPrice): TicketPrice
}
