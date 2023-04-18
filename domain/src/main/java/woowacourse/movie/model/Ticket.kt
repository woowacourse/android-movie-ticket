package woowacourse.movie.model

import woowacourse.movie.discount.MovieDiscountPolicy
import woowacourse.movie.discount.discountpolicy.DiscountPolicyAdapter
import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
) {
    fun getPaymentMoney(): Money {
        val discountPolicy = DiscountPolicyAdapter(MovieDiscountPolicy.policies)
        val discountedMoney = discountPolicy.discount(Money(TICKET_PRICE), bookedDateTime)
        return discountedMoney * count
    }

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
