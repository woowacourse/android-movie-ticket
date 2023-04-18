package woowacourse.movie.domain.price.discount.partialpolicy

import woowacourse.movie.domain.price.TicketPrice

class EarlyMorningLateNightDiscount : DiscountPolicy {
    override fun discount(price: TicketPrice): TicketPrice {
        if (price.value >= 2000) return price - 2000
        return TicketPrice(0)
    }
}
