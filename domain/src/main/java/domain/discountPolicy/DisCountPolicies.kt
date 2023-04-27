package domain.discountPolicy

import domain.Price
import domain.Ticket

data class DisCountPolicies(private val list: List<DiscountPolicy> = DEFAULT_DISCOUNT_POLICIES) {
    fun calculate(
        ticket: Ticket,
        price: Price
    ): Price {
        var presentPrice = price
        for (item in list) {
            presentPrice = item.discount(ticket, presentPrice)
        }
        return presentPrice
    }

    companion object {
        private val DEFAULT_DISCOUNT_POLICIES = listOf(MovieDay(), OffTime())
    }
}
