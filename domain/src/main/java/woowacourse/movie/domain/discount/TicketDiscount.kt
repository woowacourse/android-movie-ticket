package woowacourse.movie.domain.discount

import java.time.LocalDateTime

class TicketDiscount(
    private val time: LocalDateTime
) : DiscountPolicy {
    private val discountPolicies = listOf<DiscountPolicy>(
        MovieDayDiscount(time),
        EarlyTimeDiscount(time),
        LateTimeDiscount(time)
    )

    override fun getDiscountPrice(price: Int): Int {
        var newPrice = price
        discountPolicies.forEach { policy ->
            newPrice = policy.getDiscountPrice(newPrice)
        }
        return newPrice
    }
}
