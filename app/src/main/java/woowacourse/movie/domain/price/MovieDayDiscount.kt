package woowacourse.movie.domain.price

class MovieDayDiscount : DiscountPolicy {
    override fun discount(price: TicketPrice): TicketPrice = price * 0.9
}
