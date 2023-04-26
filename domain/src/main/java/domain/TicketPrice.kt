package domain

import domain.policy.DiscountPolicy
import domain.policy.MovieDayDiscountPolicy
import domain.policy.TimeDiscountPolicy
import java.time.LocalDateTime

data class TicketPrice(
    val price: Int = TICKET_PRICE,
    val discountPolicies: List<DiscountPolicy> = listOf(
        MovieDayDiscountPolicy(),
        TimeDiscountPolicy(),
    ),
) {

    constructor(discountPolicies: List<DiscountPolicy>) : this(TICKET_PRICE, discountPolicies)

    fun applyPolicy(dateTime: LocalDateTime): TicketPrice {
        val discountedPrice = discountPolicies.fold(price) { ticketPrice, policy ->
            calculateTicketPrice(dateTime, ticketPrice, policy)
        }
        return TicketPrice(discountedPrice, discountPolicies)
    }

    private fun calculateTicketPrice(
        dateTime: LocalDateTime,
        price: Int,
        policy: DiscountPolicy,
    ): Int {
        if (policy.checkPolicy(dateTime)) return policy.discountPrice(price)
        return price
    }

    operator fun times(count: Int): TicketPrice = TicketPrice(price * count, discountPolicies)

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
