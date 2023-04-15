package woowacourse.movie.domain.price

class EarlyMorningLateNightDiscount : DiscountPolicy {
    override fun discount(price: TicketPrice): TicketPrice {
        if (price.value >= 2000) return price - 2000
        return TicketPrice(0)
    }
}
