package woowacourse.movie.domain.model.movie

import woowacourse.movie.domain.model.movie.discount.policy.DiscountPolicy

@JvmInline
value class TicketPrice(val amount: Int = DEFAULT_TICKET_PRICE) {
    fun applyDiscountPolicy(
        vararg discountPolicies: DiscountPolicy,
    ): TicketPrice =
        TicketPrice(
            discountPolicies.fold(amount) { discounted, discountPolicy ->
                discountPolicy.discount(discounted)
            }
        )

    operator fun times(operand: Int): TicketPrice = TicketPrice(amount * operand)

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}
