package woowacourse.movie.domain.price.pricecalculate

import woowacourse.movie.domain.price.TicketPrice

interface PricePolicy {

    fun discountCalculate(price: TicketPrice): TicketPrice
}
