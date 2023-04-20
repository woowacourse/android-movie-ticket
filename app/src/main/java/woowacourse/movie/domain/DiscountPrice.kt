package woowacourse.movie.domain

import woowacourse.movie.domain.policy.DiscountPolicy
import woowacourse.movie.domain.policy.MovieDayDiscountPolicy
import woowacourse.movie.domain.policy.TimeDiscountPolicy
import java.time.LocalDateTime

class DiscountPrice(val date: LocalDateTime) {

    fun calculateTotalPrice(price: Int): Int {
        val discountPolicies = listOf(MovieDayDiscountPolicy(), TimeDiscountPolicy())
        val discountedPrice = discountPolicies.fold(price) { ticketPrice, policy ->
            calculateTicketPrice(ticketPrice, policy)
        }
        return discountedPrice
    }

    private fun calculateTicketPrice(price: Int, policy: DiscountPolicy): Int {
        if (policy.checkPolicy(date)) return policy.discountPrice(price)
        return price
    }
}
