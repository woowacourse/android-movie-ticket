package woowacourse.movie.domain

import woowacourse.movie.domain.policy.DiscountPolicy
import woowacourse.movie.domain.policy.MovieDayDiscountPolicy
import woowacourse.movie.domain.policy.TimeDiscountPolicy
import java.time.LocalDateTime

data class TicketPrice(val price: Int = TICKET_PRICE) {

    fun applyPolicy(dateTime: LocalDateTime): TicketPrice {
        val discountPolicies = listOf(MovieDayDiscountPolicy(), TimeDiscountPolicy())
        val discountedPrice = discountPolicies.fold(price) { ticketPrice, policy ->
            calculateTicketPrice(dateTime, ticketPrice, policy)
        }
        return TicketPrice(discountedPrice)
    }

    private fun calculateTicketPrice(
        dateTime: LocalDateTime,
        price: Int,
        policy: DiscountPolicy,
    ): Int {
        if (policy.checkPolicy(dateTime)) return policy.discountPrice(price)
        return price
    }

    operator fun times(count: Int): Ticket = Ticket(price * count)

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
