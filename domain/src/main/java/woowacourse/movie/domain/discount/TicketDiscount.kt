package woowacourse.movie.domain.discount

import woowacourse.movie.domain.PeopleCount
import java.time.LocalDateTime

class TicketDiscount(
    private val time: LocalDateTime,
    private val count: PeopleCount
) : DiscountPolicy {
    private val discountPolicies = listOf<DiscountPolicy>(
        MovieDayDiscount(time),
        EarlyTimeDiscount(time, count),
        LateTimeDiscount(time, count)
    )

    override fun getDiscountPrice(price: Int): Int {
        var newPrice = price
        discountPolicies.forEach { policy ->
            newPrice = policy.getDiscountPrice(newPrice)
        }
        return newPrice
    }
}
