package woowacourse.movie.domain

import woowacourse.movie.domain.discountPolicy.DisCountPolicies
import woowacourse.movie.domain.discountPolicy.DiscountPolicy
import java.time.LocalDateTime

data class Ticket(
    val date: LocalDateTime,
    val peopleCount: Int,
    val disCountPolicies: DisCountPolicies
) {
    val totalPrice: Price
        get() = Price(peopleCount * getDiscountPrice().value)

    fun addDisCountPolicy(discountPolicy: DiscountPolicy) {
        disCountPolicies.addPolicy(discountPolicy)
    }
    fun deleteDisCountPolicy(discountPolicy: DiscountPolicy) {
        disCountPolicies.deletePolicy(discountPolicy)
    }

    private fun getDiscountPrice(): Price =
        disCountPolicies.calculate(this, Price(DEFAULT_TICKET_PRICE))
    companion object {
        private const val DEFAULT_TICKET_PRICE = 13000
    }
}
