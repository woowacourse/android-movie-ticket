package woowacourse.movie.domain.price.discount.partialpolicy

import woowacourse.movie.domain.price.TicketPrice

class MovieDayDiscount : DiscountPolicy {
    override fun discount(price: TicketPrice): TicketPrice = price * 0.9
}
