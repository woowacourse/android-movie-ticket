package woowacourse.movie.domain.price

interface DiscountPolicy {
    fun discount(price: TicketPrice): TicketPrice
}
