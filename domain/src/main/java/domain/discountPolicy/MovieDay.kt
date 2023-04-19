package domain.discountPolicy

import domain.Price
import domain.Ticket

class MovieDay(private val movieDay: List<Int> = DEFAULT_MOVIE_DAY) : DiscountPolicy {
    override fun discount(ticket: Ticket, price: Price): Price {
        if (ticket.date.dayOfMonth in movieDay) {
            return Price((price.value * DISCOUNT_RATE).toInt())
        }
        return price
    }

    companion object {
        private val DEFAULT_MOVIE_DAY = listOf(10, 20, 30)
        private const val DISCOUNT_RATE = 0.9f
    }
}
