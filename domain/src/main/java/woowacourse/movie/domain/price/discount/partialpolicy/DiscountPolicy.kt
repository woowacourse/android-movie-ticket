package woowacourse.movie.domain.price.discount.partialpolicy

import woowacourse.movie.domain.price.TicketPrice

interface DiscountPolicy {
    fun discount(price: TicketPrice): TicketPrice
}
