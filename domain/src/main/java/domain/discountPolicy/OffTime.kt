package domain.discountPolicy

import domain.Price
import domain.Ticket

class OffTime(private val earlyTime: Int = DEFAULT_EARLY_TIME, private val lateTime: Int = DEFAULT_LATE_TIME) :
    DiscountPolicy {
    override fun discount(ticket: Ticket, price: Price): Price {
        if (ticket.date.hour < earlyTime || ticket.date.hour > lateTime) {
            return Price(price.value - DISCOUNT_VALUE)
        }
        return price
    }

    companion object {
        private const val DEFAULT_EARLY_TIME = 11
        private const val DEFAULT_LATE_TIME = 20
        private const val DISCOUNT_VALUE = 2000
    }
}
